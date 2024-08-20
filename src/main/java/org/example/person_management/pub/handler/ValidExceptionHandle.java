package org.example.person_management.pub.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.person_management.pub.result.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @author: 15713
 * @date: 2024/8/15
 */
@RestControllerAdvice
@Slf4j
public class ValidExceptionHandle {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleValidException(MethodArgumentNotValidException e){
        if(Objects.isNull(e.getBindingResult().getFieldError())){
            return Result.fail("未知错误，没有捕获到报错信息");
        }
        log.error(e.getBindingResult().getFieldError().getDefaultMessage());
        return Result.fail(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
