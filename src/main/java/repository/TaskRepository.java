package repository;

import model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findByUser(String user);
}
