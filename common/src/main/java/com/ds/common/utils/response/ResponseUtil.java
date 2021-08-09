package com.ds.common.utils.response;

import com.ds.common.enumerate.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author raptor
 * @description ResponseUtil
 * @date 2021/7/6 15:00
 */
public class ResponseUtil {

    public static void response(HttpServletRequest request, HttpServletResponse response,int httpServletResponse, ResultEnum resultEnum) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(httpServletResponse);
        PrintWriter out = response.getWriter();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", resultEnum.getCode());
        map.put("msg", resultEnum.getMsg());
        out.write(new ObjectMapper().writeValueAsString(map));
        out.flush();
        out.close();
    }


    public static void response(HttpServletRequest request, HttpServletResponse response, Map<String,Object> result) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(result));
        out.flush();
        out.close();
    }
}
