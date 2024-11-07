package com.springb.springb.Task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/TP04")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;   
    @GetMapping("/task1")
    public String getTask1(Model model) {
        model.addAttribute("variable", "Hello world");
        List<Task> tasks = taskRepository.findAll();
        Task task = new Task();
        model.addAttribute("tasks", tasks);
        model.addAttribute("newTask", task);
        return "task/task01";
    }
    @PostMapping("/savetask")
    public String redirectToTask(@ModelAttribute("task") Task task){
        taskRepository.save(task);
        return "redirect:/TP04/task1";
    }
        
}
