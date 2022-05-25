package com.example.asd.web.controllers;

import com.example.asd.data.entities.Task;
import com.example.asd.exceptions.*;

import com.example.asd.utils.DateUtils;
import com.example.asd.web.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/tasks")
public class TaskConreoller {

    @Autowired
    TaskService taskService;

    private void addDate(Model model, String taskDate) {
        if (taskDate.equals("today")) {
            model.addAttribute("date", DateUtils.getToday());
            model.addAttribute("dateUnformat", "today");
        } else if (taskDate.equals("tomorrow")) {
            model.addAttribute("date", DateUtils.getTomorrow());
            model.addAttribute("dateUnformat", "tomorrow");
        } else {
            model.addAttribute("date", taskDate);
            model.addAttribute("dateUnformat", taskDate);

        }

    }

    private void redirect(String userid, Model model, String tasksDate) {
        if (tasksDate.equals("all")) {
            model.addAttribute("tasks", taskService.readAll(userid));
        } else if (tasksDate.equals("today")) {
            model.addAttribute("tasks", taskService.readAllByDate(userid,DateUtils.getToday()));
        } else if (tasksDate.equals("tomorrow")) {
            model.addAttribute("tasks", taskService.readAllByDate(userid,DateUtils.getTomorrow()));
        } else if (tasksDate.equals("this-month")) {
            String[] datemonth = DateUtils.getThisMonth();
            model.addAttribute("tasks", taskService.readByYearAndMonth(userid,datemonth[0], datemonth[1]));
        } else if (DateUtils.isYYYYMMDD(tasksDate)) {
            model.addAttribute("tasks", taskService.readAllByDate(userid,tasksDate));
        } else if (DateUtils.isYYYYMM(tasksDate)) {
            String[] splitPageDate = tasksDate.split("-");
            model.addAttribute("tasks", taskService.readByYearAndMonth(userid,splitPageDate[0], splitPageDate[1]));
        } else if (DateUtils.isYYYY(tasksDate)) {
            model.addAttribute("tasks", taskService.readByYear(userid,Integer.parseInt(tasksDate)));
        } else {
            throw new IncorrectDateException(tasksDate);
        }
    }


    @GetMapping("/choosedate")
    public String showMenu(Principal principal, Model model) {
        model.addAttribute("user", principal.getName());
        return "choose-date";
    }

    @GetMapping("/{tasksDate}")
    public String showTasks(Principal principal, @PathVariable String tasksDate, Model model) {
        redirect(principal.getName(),model, tasksDate);
        addDate(model, tasksDate);
        model.addAttribute("user", principal.getName());
        return "tasks";
    }

    @PostMapping("/{tasksDate}")
    public String getTasks(
            Principal principal,
            @PathVariable String tasksDate,
            @RequestParam String name,
            @RequestParam int priority,
            @RequestParam String date,
            @RequestParam String time, Model model) {

        taskService.create(principal.getName(), name, priority, date, time);
        redirect(principal.getName(), model, tasksDate);
        addDate(model, tasksDate);
        model.addAttribute("user", principal.getName());
        return "tasks";
    }

    @PostMapping("/{tasksDate}/d")
    RedirectView deleteTask(Principal principal,@PathVariable String tasksDate,
                            @RequestParam Long id, Model model) {
        Task task = taskService.readById(principal.getName(),id).orElseThrow(() -> new NoSuchTaskException(id));
        taskService.delete(task);
        redirect(principal.getName(),model, tasksDate);
        addDate(model, tasksDate);
        model.addAttribute("user", principal.getName());
        return new RedirectView("/tasks/" + tasksDate);
    }

    @PostMapping("/{tasksDate}/r")
    RedirectView redactTask(Principal principal,
                            @PathVariable String tasksDate,
                            @RequestParam Long id, @RequestParam String name,
                            @RequestParam int priority,
                            @RequestParam String date,
                            @RequestParam String time,
                            Model model) {
        taskService.update(principal.getName(),id, name, priority, date, time);
        redirect(principal.getName(),model, tasksDate);
        addDate(model, tasksDate);
        model.addAttribute("user", principal.getName());
        return new RedirectView("/tasks/" + tasksDate);
    }
}
