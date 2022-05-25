package com.example.asd.exceptions.handlers;

import org.springframework.ui.Model;
import com.example.asd.exceptions.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAlreadyExistsExceptionHandler {

        @ExceptionHandler(UserAlreadyExistsException.class)
        public String noSuchTaskHandler(UserAlreadyExistsException ex, Model model){
            model.addAttribute("error", ex.getMessage());
            return "signup";
        }

}
