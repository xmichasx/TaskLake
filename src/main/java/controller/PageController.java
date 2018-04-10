package controller;

import model.Task;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repository.DataUser;
import repository.TaskRepository;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {
    @Autowired
    private DataUser dataUser;
    @Autowired
    private TaskRepository taskRepository;
    private User userG = new User();

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        if(!userG.isLoggedIn()){
            model.addAttribute("user", userG);
            return new ModelAndView("index");
        }
        // TODO review: niepotrzebny 'else' jeśli w 'if' uzywamy 'return' (dotyczy całej tej klasy)
        else{
            return new ModelAndView("redirect:/userList");
        }
    }
    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView indexPOST(User user) {
        userG=dataUser.logIn(user.getLogin(),user.getPassword());
        if(null==userG || !userG.isLoggedIn()){
            return new ModelAndView("index");
        }
        else{
            return new ModelAndView("redirect:/userList");
        }
    }
    // TODO review: brakuje określenia metody HTTP
    @RequestMapping("userList")
    public ModelAndView userList(Model model) {
        if(null==userG || !userG.isLoggedIn()){
            return new ModelAndView("redirect:/");
        }
        else {
            List<Task> listOfTask=null;
            if(!userG.isAdmin()){
                try{
                    listOfTask= taskRepository.findByUser(userG.getLogin());
                }
                catch(Exception ex){
                    System.out.println("Error "+ex.toString());
                }
            }
            else{
                try{
                    listOfTask= taskRepository.findAll();
                }
                catch(Exception ex){
                    System.out.println("Error "+ex.toString());
                }
            }
            model.addAttribute("dataTask", listOfTask);
            model.addAttribute("user",userG);
            return new ModelAndView("list");
        }
    }
    @RequestMapping(value="edit", method = RequestMethod.GET)
    public ModelAndView editStatus(@ModelAttribute("taskId") long taskId, Model model ) {
        if(dataUser.doAdmin(userG)){
            // TODO review: użytkownik nie powinien zostać wylogowany w takiej sytuacji, raczej powinien zobaczyć informacje, że nie ma odpowiednich uprawnień (dotyczy wszystkich takich miejsc w tej klasie)
            return new ModelAndView("redirect:/logout");
        }
        else{
            Optional<Task> taskOptional = taskRepository.findById(taskId);
            // TODO review: tutaj ładnie można zastosować metodę 'orElse' z klasy Optional
            Task task=null;
            if(taskOptional.isPresent()){
                task = taskOptional.get();
            }
            String status="";
            model.addAttribute("task",  task);
            model.addAttribute("status", status);
            return new ModelAndView("editTask");
        }
    }
    @RequestMapping(value="update", method = RequestMethod.POST)
    public ModelAndView updateStatus(@ModelAttribute("taskId") long taskId, @ModelAttribute("status") String status ) {
        if(dataUser.doAdmin(userG)){
            return new ModelAndView("redirect:/logout");
        }
        else{
            Optional<Task> taskOptional = taskRepository.findById(taskId);

            Task task=null;
            if(taskOptional.isPresent()){
                task = taskOptional.get();
            }
            task.setStatus(status);
            taskRepository.save(task);
            return new ModelAndView("redirect:/userList");
        }
    }
    @RequestMapping(value="add", method = RequestMethod.GET)
    public ModelAndView addTaskForm(Model model ) {
        if(dataUser.doAdmin(userG)){
            return new ModelAndView("redirect:/logout");
        }
        else{
            Task task= new Task();
            model.addAttribute("task", task);
            model.addAttribute("user",dataUser.listOfuser());
            return new ModelAndView("addTask");
        }
    }
    @RequestMapping(value="add", method = RequestMethod.POST)
    public ModelAndView addTask(Task task, Model model) {
        if(dataUser.doAdmin(userG)){
            return new ModelAndView("redirect:/logout");
        }
        else{
            try{
                taskRepository.save(task);
            }
            catch(Exception ex){
                System.out.println("Error "+ex.toString());
            }
            return new ModelAndView("redirect:/userList");
        }
    }
    @RequestMapping(value="logout")
    public ModelAndView logOut() {
        if(userG.isLoggedIn()){
            userG=dataUser.logout();
        }
        return new ModelAndView("redirect:/");
    }
}