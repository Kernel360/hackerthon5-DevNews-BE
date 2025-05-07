package org.example.devnews.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.example.devnews.dto.ResponseDto;

import java.io.IOException;

public class LoginResponseUtil {

    public static void success(HttpServletResponse response, Object dto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto responseDto = new ResponseDto(1, "로그인성공", dto);
        String responseBody = objectMapper.writeValueAsString(responseDto);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(200);
        response.getWriter().println(responseBody);
    }

    public static void fail(HttpServletResponse response, String msg, int code) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDto responseDto = new ResponseDto(-1, "인증안됨", null);
        String responseBody = objectMapper.writeValueAsString(responseDto);
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(code);
        response.getWriter().println(responseBody);
    }

}