package com.example.asd.exceptions.handlers;

import  com.example.asd.exceptions.*;
import org.springframework.http.HttpMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class IncorrectDateExceptionHandler {
    @ExceptionHandler(IncorrectDateException.class)
    public RedirectView handler(IncorrectDateException ex, Model model){
         model.addAttribute("error", ex.getMessage());
        return new RedirectView("/tasks/all");
    }
}
