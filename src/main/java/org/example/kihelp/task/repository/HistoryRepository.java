package org.example.kihelp.task.repository;

import org.example.kihelp.task.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserId(Long userId);
    List<History> findAllByTaskId(Integer taskId);
}
