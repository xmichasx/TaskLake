package dataImpl;

import data.DataTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class DataTaskImpl implements DataTask {

    private List<Task> listOfTask = new ArrayList<>();

    public DataTaskImpl() {
        Task task1 = new Task();
        task1.setTaskId((long) 1);
        task1.setName("zadanie 1");
        task1.setDescription("Opis 1");
        task1.setStatus("status1");
        task1.setUser("user1");
        listOfTask.add(task1);


        Task task2 = new Task();
        task2.setTaskId((long) 2);
        task2.setName("zadanie 2");
        task2.setDescription("Opis 2");
        task2.setStatus("status2");
        task2.setUser("user1");
        listOfTask.add(task2);



        Task task3 = new Task();
        task3.setTaskId((long) 3);
        task3.setName("zadanie 3");
        task3.setDescription("Opis 3");
        task3.setStatus("status3");
        task3.setUser("user2");
        listOfTask.add(task3);

    }

    @Override
    public List<Task> getTasks() {
        return listOfTask;
    }

    @Override
    public List<Task> getTaskByUserName(String name) {
        List<Task> listId=new ArrayList<>();
        for(Task i: listOfTask){
            if(name.equals(i.getUser())){
                listId.add(i);
            }
        }
        return listId;
    }

    @Override
    public Task getTask(int taskId) {
        Task task = new Task();
        for(Task i: listOfTask){
            if(i.getTaskId()==taskId){
                task=i;
            }
        }
        return task;


    }

    @Override
    public void addTask(Task task) {
        listOfTask.add(task);

    }

    @Override
    public void updateStatus(int taskId, String status) {
        int r=0;
        for(Task i: listOfTask){
            if(i.getTaskId()==taskId){
                i.setStatus(status);
//                r=listOfTask.indexOf(i);
//                i.setStatus(status);
//                listOfTask.set(r,i);
                break;
            }
        }

    }
}
