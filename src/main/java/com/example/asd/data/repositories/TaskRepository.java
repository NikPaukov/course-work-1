package com.example.asd.data.repositories;

import com.example.asd.data.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUsernameAndDate(String userid, String date);
    List<Task> findAllByUsernameAndYearAndMonth(String userid, int year, int month);
    List<Task> findAllByUsernameAndYear(String userid, int year);
    List<Task> findAllByUsername(String userid);
    Optional<Task> findByUsernameAndId(String userid, Long id);
}
