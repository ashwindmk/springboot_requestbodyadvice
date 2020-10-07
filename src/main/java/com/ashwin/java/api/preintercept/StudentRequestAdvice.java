package com.ashwin.java.api.preintercept;

import com.ashwin.java.api.controller.StudentController;
import com.ashwin.java.domain.model.Student;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestControllerAdvice
public class StudentRequestAdvice implements RequestBodyAdvice {
    private static final String TAG = StudentRequestAdvice.class.getName();

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println(TAG + " | supports | methodParameter name: " + methodParameter.getParameterName() + ", parameter: " + methodParameter.getParameter().getName() + " | class: " + aClass.getName());
        if (methodParameter.getContainingClass() == StudentController.class && type.getTypeName().equals(Student.class.getTypeName())) {
            // Forward request to below methods
            return true;
        }
        return false;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        System.out.println(TAG + " | beforeBodyRead | methodParameter: " + methodParameter + " | type: " + type.getTypeName());
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println(TAG + " | afterBodyRead | body: " + body +  " | methodParameter: " + methodParameter + " | type: " + type.getTypeName());
        Student student = (Student) body;
        student.setTimestamp(LocalDateTime.now(ZoneOffset.UTC));
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        System.out.println(TAG + " | handleEmptyBody | methodParameter: " + methodParameter + " | type: " + type.getTypeName());
        return body;
    }
}
