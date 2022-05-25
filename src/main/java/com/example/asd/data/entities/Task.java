package com.example.asd.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tasks")
public class Task {
    @Column
    private String name;

    @Column
    private String username;
    @Column
    private int priority;

    @Column
    private int year;
    @Column
    private int month;
    @Column
    private int day;

    public Task(String name, String userid, int priority, int year, int month, int day, String time) {
        this.name = name;
        this.username = userid;
        this.priority = priority;
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
        this.date=String.format("%s-%s-%s", year, month>9?String.valueOf(month):"0"+String.valueOf(month),
                day>9?String.valueOf(day):"0"+String.valueOf(day));    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", userid='" + username + '\'' +
                ", priority=" + priority +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return priority == task.priority && year == task.year && month == task.month && day == task.day &&
                Objects.equals(name, task.name) && Objects.equals(username, task.username) &&
                Objects.equals(time, task.time) && Objects.equals(date, task.date) && Objects.equals(id, task.id);
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return username;
    }

    public void setUserid(String userid) {
        this.username = userid;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    private String time;
    @Column
    private String date;
    @Column
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

}
