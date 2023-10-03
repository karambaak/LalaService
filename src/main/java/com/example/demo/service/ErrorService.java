package com.example.demo.service;

import com.example.demo.errors.ErrorResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ErrorService {

    public ErrorResponseBody makeBody(BindingResult result) {
        List<String> errors = new ArrayList<>();

        for (ObjectError error : result.getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }
        printErrorLog(errors.toString());
        return ErrorResponseBody.builder()
                .title("Validation failed")
                .reasons(errors)
                .build();
    }

    public ErrorResponseBody makeBody(Exception exception) {
        ErrorResponseBody erb = ErrorResponseBody.builder()
                .title(exception.getMessage())
                .reasons(List.of(exception.getMessage()))
                .build();
        printErrorLog(erb.getReasons().toString());
        return erb;
    }

    private void printErrorLog(String message) {
        log.error("Exception message: {}", message);
    }
}
