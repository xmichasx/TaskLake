package data;

import model.Task;

import java.util.List;

public interface DataTask {

    List<Task> getTasks();
    List<Task> getTaskByUserName(String name);
    Task getTask(int taskId);
    void addTask(Task task);
    void updateStatus(int taskId, String status);

}
