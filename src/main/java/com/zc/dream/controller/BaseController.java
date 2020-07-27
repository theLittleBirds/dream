package com.zc.dream.controller;

import com.zc.dream.core.ServiceException;
import com.zc.dream.util.result.Result;
import com.zc.dream.util.result.ResultGenerator;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    protected HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected void hasObject(Object object, String objectName) throws ServiceException {

        if (object == null) {
            throw new ServiceException(objectName+"空");
        }
    }

    protected Result isNull(String result){
        if(StringUtils.isEmpty(result)){
            return ResultGenerator.genSuccessResult(null);
        }else {
            return ResultGenerator.genSuccessResult(result);
        }
    }
}