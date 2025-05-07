package org.example.devnews.validate.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import org.example.devnews.ex.CustomValidationException;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CustomValidationAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping(){}

    @Around("postMapping() || putMapping()")
    public Object validationAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;
                if(bindingResult.hasErrors()) {

                    Map<String, String> errorMap = new HashMap<>();
                    bindingResult.getFieldErrors().forEach(fieldError -> {errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());});
                    throw new CustomValidationException("유효성검사 실패", errorMap);
                }
            }
        }
        return joinPoint.proceed();
    }
}
