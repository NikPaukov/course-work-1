package com.example.asd.web.services;

import com.example.asd.data.entities.Task;
import com.example.asd.data.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService{
TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> readAll(String userid){
        return taskRepository.findAllByUsername(userid);
    }
    public Optional<Task> readById(String userid, Long id){
        return taskRepository.findByUsernameAndId(userid, id);
    }
    public List<Task> readByYearAndMonth(String userid, String year, String month){
        return taskRepository.findAllByUsernameAndYearAndMonth(userid, Integer.parseInt(year), Integer.parseInt(month));
    }
    public List<Task> readByYear(String userid, int year){
        return taskRepository.findAllByUsernameAndYear(userid, year);
    }
    public Task update(String userid, Long id, String name, int priority, String date, String time){
        int[] split = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();

        if (readById(userid, id).isPresent()) {
            readById(userid, id).map(task -> {
                task.setName(name);
                task.setPriority(priority);
                task.setYear(split[0]);
                task.setMonth(split[1]);
                task.setDay(split[2]);
                task.setDate(String.format("%s-%s-%s", split[0],
                        split[1]>9?String.valueOf(split[1]):"0"+String.valueOf(split[1]),
                        split[2]>9?String.valueOf(split[2]):"0"+String.valueOf(split[2])));
                task.setTime(time);
                return create(task);
            });
            return readById(userid, id).get();
        }
        else {
            Task newTask = new Task(name,userid, priority, split[0], split[1], split[2], time);
            newTask.setId(id);
            return create(newTask);
        }
    }
    public List<Task> readAllByDate(String userid, String date){
        return  taskRepository.findAllByUsernameAndDate(userid, date);
    }
    public Task create(Task task){
        return taskRepository.save(task);
    }
    public Task create(String userid, String name, int priority, String date, String time) {

        int[] splitTaskDate = Arrays.stream(date.split("-")).mapToInt(Integer::parseInt).toArray();
        return  taskRepository.save(new Task(name,userid, priority, splitTaskDate[0], splitTaskDate[1],
                splitTaskDate[2], time));
    }
    public void delete(Task task){
        taskRepository.delete(task);
    }

}
