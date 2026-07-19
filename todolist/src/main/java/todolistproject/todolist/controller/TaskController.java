package todolistproject.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import todolistproject.todolist.dto.TaskDto;
import todolistproject.todolist.service.TaskService;

import java.security.Principal;


@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/")
    public String viewTaskList(Model model, Principal principal){
        model.addAttribute("tasks",taskService.getAllTasks());
        model.addAttribute("username", principal.getName());
        return "task-list";
    }

    @GetMapping("/tasks/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new TaskDto());
        return "add-task";
    }

    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute("task") TaskDto taskDto) {
        taskService.saveTask(taskDto);
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}")
    public String viewTaskDetail(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "task-detail";
    }

    @GetMapping("/tasks/{id}/edit")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "edit-task";
    }

    @PostMapping("/tasks/{id}/edit")
    public String editTask(@PathVariable Long id, @ModelAttribute("task") TaskDto taskDto) {
        taskService.updateTask(id, taskDto);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/tasks/{id}/done")
    public String markTaskAsDone(@PathVariable Long id) {
        taskService.markAsDone(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/";
    }
}
