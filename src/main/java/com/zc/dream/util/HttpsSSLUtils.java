package com.zc.dream.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class HttpsSSLUtils {

	private static final String MAC_NAME = "HmacSHA256";

	public static void main(String[] args) throws Exception {
	}

	@SuppressWarnings("unused")
	public static String dqyDataClient(String URL,String token) throws Exception {
		try {
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			SSLSocketFactory oldSocketFactory = null;
			HostnameVerifier oldHostnameVerifier = null;
			boolean useHttps = URL.startsWith("https");
			if (useHttps) {
				HttpsURLConnection https = (HttpsURLConnection) connection;
				oldSocketFactory = trustAllHosts(https);
				oldHostnameVerifier = https.getHostnameVerifier();
				https.setHostnameVerifier(DO_NOT_VERIFY);

				https.setDoOutput(true);
				//请求头
				https.setRequestProperty("Pragma", "no-cache");
				https.setRequestProperty("Cache-Control", "no-cache");
				https.setRequestProperty("Content-Type","application/json");
				https.setRequestProperty("token",token);
				OutputStreamWriter out = new OutputStreamWriter(https.getOutputStream(), "utf-8");

				//请求体
				out.flush();
				out.close();
				BufferedReader br = new BufferedReader(new InputStreamReader(https.getInputStream()));
				StringBuffer lines = new StringBuffer();
				String line = "";
				for (line = br.readLine(); line != null; line = br.readLine()) {
					lines.append(line);
				}
				return lines.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 覆盖java默认的证书验证
     */
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }};

    /**
     * 设置不验证主机
     */
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 信任所有
     * @param connection
     * @return
     */
    private static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }

	/**
	 * sha256_HMAC加密
	 * base64加密
	 *
	 * @param message 消息
	 * @param secret  秘钥
	 * @return 加密后字符串
	 */
	private static String sha256HMAC_Base64(String message, String secret) {
		String hash = "";
		try {
			Mac sha256_HMAC = Mac.getInstance(MAC_NAME);
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), MAC_NAME);
			sha256_HMAC.init(secret_key);
			byte[] bytes = sha256_HMAC.doFinal(message.getBytes());//sha256加密
			Base64.Encoder encoder = Base64.getEncoder();
			hash = encoder.encodeToString(bytes);
		} catch (Exception e) {
			System.out.println("Error HmacSHA256 ===========" + e.getMessage());
		}
		return hash;
	}
}
