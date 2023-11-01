package co.deepmindz.adminmainservice.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.deepmindz.adminmainservice.dto.LoginModeStatusDto;

public class CustomConfigHttpResponse {

    public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus,
    		LoginModeStatusDto loginMode, Object responseObject, Object appStatics){

        Map<String, Object> response = new HashMap<>();
        response.put("data", responseObject);
        response.put("loginMode", loginMode);

        response.put("appStatics", appStatics);

        response.put("httpStatus", httpStatus);
        response.put("message", message);

        return new ResponseEntity<>(response, httpStatus);
    }
}
