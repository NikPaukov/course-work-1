package com.example.asd.web.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
@Controller
public class SimpleErrorController implements ErrorController {
    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("error", "Сторінка не знайдена");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("error", "Спочатку авторизуйтесь! <br><a href='/login'>Увійти</a>");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("error",     "Виникла внутрішня помилка сервера");
            }
        }
        return "errorPage";
    }
}
