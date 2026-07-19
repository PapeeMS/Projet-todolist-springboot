package todolistproject.todolist.service;

import java.util.List;

import todolistproject.todolist.dto.TaskDto;
import todolistproject.todolist.entity.Task;
import todolistproject.todolist.entity.Utilisateur;
import todolistproject.todolist.mapper.TaskMapper;
import todolistproject.todolist.repository.TaskRepository;
import todolistproject.todolist.repository.UtilisateurRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findByUtilisateur(getAuthenticatedUtilisateur());
        return taskMapper.toTaskDtos(tasks);
    }
    
    @Override
    public TaskDto getTaskById(Long id) {
        TaskDto taskDto = taskMapper.toTaskDto(getAuthenticatedTask(id));
        return taskDto;
    }
    
    @Override
    public void saveTask(TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        task.setUtilisateur(getAuthenticatedUtilisateur());
        taskRepository.save(task);
    }
    
    @Override
    public void deleteTaskById(long id) {
        taskRepository.delete(getAuthenticatedTask(id));
    }
    
    @Override
    public void updateTask(Long id, TaskDto taskDto) {
        Task updateTask = getAuthenticatedTask(id);
        updateTask.setTitle(taskDto.getTitle());
        updateTask.setTaskDescription(taskDto.getTaskDescription());
        updateTask.setDeadline(taskDto.getDeadline());
        updateTask.setIsDone(taskDto.getIsDone());
        taskRepository.save(updateTask);
    }
    
    @Override
    public void markAsDone(long id) {
        Task task = getAuthenticatedTask(id);
        task.setIsDone(true);
        taskRepository.save(task);
    }

    private Task getAuthenticatedTask(Long id) {
        return taskRepository.findByIdAndUtilisateur(id, getAuthenticatedUtilisateur())
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    private Utilisateur getAuthenticatedUtilisateur() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable: " + username));
    }
}
