package com.zc.dream.util;

public class IdentifierGenerator {

    public static String getUserId() throws Exception {
        return(SnowFlake.getSnowflakeId(Constants.ID_PREFIX_USER));
    }

    public static String getPositionId() throws Exception {
        return(UuidUtils.getUNIDI(Constants.ID_PREFIX_POSITION,9));
    }

    public static String getCustomerId() throws Exception {
        return(SnowFlake.getSnowflakeId(Constants.ID_PREFIX_CUSTOMER));
    }

    public static String getBusinessId() throws Exception {
        return(SnowFlake.getSnowflakeId(Constants.ID_PREFIX_BUSINESS));
    }

}
