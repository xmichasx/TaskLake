package controller;

import repository.DataUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import model.Task;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import repository.TaskRepository;

import java.util.List;
import java.util.Optional;

// TODO review: niepotrzebny komentarz
@Controller // aplikacja będzie wiedziała że nasza klasa obsługuje żądania http
public class PageController {

    // TODO review: elementy składowe klasy (pola i metody) powinny posiadać kwantyfikator dostępu
    @Autowired
    DataUser dataUser;

    @Autowired
    TaskRepository taskRepository;

    User userG = new User();

    // TODO review: śmieci
    //DataTask dataTask = new DataTaskImpl()
    //boolean zalogowany = false;
   // boolean admin = true;
    //boolean user = false;

    @RequestMapping(value="/", method = RequestMethod.GET)
    // TODO review: niepotrzebny komentarz
    //@ResponseBody // mówi że metoda zwróci ciało. to co zostanie zwrócone zostanie przesłąne do przeglądarki
    public ModelAndView index(Model model) {
        if(!userG.isLoggedIn()){

            model.addAttribute("user", userG);
            return new ModelAndView("index");
        }
        else{

            return new ModelAndView("redirect:/ListaUser");
        }

    }

    // TODO review: konwencja nazewnicza - używanie polskich nazw nie jest dobrą praktyką,
    // TODO review: a jeśli już decydujemy się na polskie nazwy to powinniśmy trzymać się tej zasady w kontekście całej aplikacji
    @RequestMapping(value="/", method = RequestMethod.POST)
    public ModelAndView indexObsluga(User user) {

        System.out.println(user.getLogin());
        // TODO review: wyswietlanie hasla w konsoli nie jest dobrym pomyslem
        System.out.println(user.getPassword());

        userG=dataUser.logIn(user.getLogin(),user.getPassword());
        if(null==userG || !userG.isLoggedIn()){

            return new ModelAndView("index");
        }
        else{
            return new ModelAndView("redirect:/ListaUser");
            // TODO review: śmieci
//            {

//            if(userG.isAdmin()){
//                return new ModelAndView("redirect:/ListaUser");
//            }
//            else{
//
//                return new ModelAndView("redirect:/ListaUser");
//            }

        }
    }

    @RequestMapping("ListaUser")
    public ModelAndView listaUser(Model model) {

        if(null==userG || !userG.isLoggedIn()){

            return new ModelAndView("redirect:/");
        }
        // TODO review: 'else if' jest tutaj nadmiarowe jeśli w poprzednim 'if' użyliśmy 'return'
        else if(!userG.isAdmin()){
            // TODO review: większość bloku kodu w 'else if' i 'else' jest powtórzona, może
            List<Task> listOfTask=null;
            try{
                // TODO niepotrzebne rzutowanie
                listOfTask= (List<Task>)taskRepository.findByUser(userG.getLogin());
            }
            catch(Exception ex){
                // TODO review: to nie jest dobra metoda na obsługę wyjątków
                System.out.println("Error "+ex.toString());
            }

            model.addAttribute("dataTask", listOfTask);
            return new ModelAndView("listaUser");
        }
        else{
            //int taskId=0;
            List<Task> listOfTask=null;
            try{

                listOfTask= (List<Task>)taskRepository.findAll();
            }
            catch(Exception ex){
                System.out.println("Error "+ex.toString());
            }


            //model.addAttribute("dataTask", listOfTask);
            model.addAttribute("dataTask",listOfTask);
            //model.addAttribute("taskId",taskId);
            return new ModelAndView("listaAdmin");
        }

    }

    @RequestMapping(value="edit", method = RequestMethod.GET)
    public ModelAndView editStatus(@ModelAttribute("taskId") long taskId, Model model ) {

        if(null==userG || !userG.isLoggedIn()){

            return new ModelAndView("redirect:/");
        }
        else if(!userG.isAdmin()){

            return new ModelAndView("index");
        }
        else{

            Optional<Task> taskOptional = taskRepository.findById(taskId);
            // TODO review: w przypadku klasy Optional, przed wywołaniem metody 'get()' warto wywołać metodę 'isPresent()'
            Task task = taskOptional.get();
            String status="";
            model.addAttribute("task",  task);
            model.addAttribute("status", status);
            return new ModelAndView("editTask");
        }

    }

    @RequestMapping(value="update", method = RequestMethod.POST)
    public ModelAndView updateStatus(@ModelAttribute("taskId") long taskId, @ModelAttribute("status") String status ) {

        if(null==userG || !userG.isLoggedIn()){

            return new ModelAndView("redirect:/");
        }
        else if(!userG.isAdmin()){

            return new ModelAndView("index");
        }
        else{
            Optional<Task> taskOptional = taskRepository.findById(taskId);
            Task task = taskOptional.get();

            task.setStatus(status);
            taskRepository.save(task);

            return new ModelAndView("redirect:/ListaUser");
        }

    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public ModelAndView addTaskForm(Model model ) {

        if(null==userG || !userG.isLoggedIn()){

            return new ModelAndView("redirect:/");
        }
        else if(!userG.isAdmin()){

            return new ModelAndView("index");
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

        if(null==userG || !userG.isLoggedIn()){

            return new ModelAndView("redirect:/");
        }
        else if(!userG.isAdmin()){

            return new ModelAndView("index");
        }
        else{
            try{

                taskRepository.save(task);
            }
            catch(Exception ex){
                System.out.println("Error "+ex.toString());
            }
            return new ModelAndView("redirect:/ListaUser");
        }

    }

    @RequestMapping(value="logout")
    public ModelAndView logOut() {
        if(userG.isLoggedIn()){
            userG=dataUser.logout();

        }
        return new ModelAndView("redirect:/");


    }

//
//
//a
//    @RequestMapping("ListaAdmin")
//    public ModelAndView listaAdmin() {
//        if(admin){
//            return new ModelAndView("listaAdmin");
//        }
//        else if(false){
//            return new ModelAndView("brakUprawnien");
//        }
//        else{
//            return new ModelAndView("index");
//        }
//
//    }

    // TODO review: ???
    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hi!";
    }
}