package com.example.asd.exceptions.handlers;

import com.example.asd.exceptions.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoSuchTaskExceptionHandler {
@ExceptionHandler(NoSuchTaskException.class)
public String noSuchTaskHandler(NoSuchTaskException ex, Model model){
    model.addAttribute("error", ex.getMessage());
    return "errorPage";
}}
