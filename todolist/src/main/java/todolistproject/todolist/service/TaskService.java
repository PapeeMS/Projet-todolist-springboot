package todolistproject.todolist.service;

import java.util.List;

import todolistproject.todolist.dto.TaskDto;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    public List<TaskDto> getAllTasks();
    public TaskDto getTaskById(Long id);
    public void saveTask(TaskDto taskDto);
    public void deleteTaskById(long id);
    void updateTask(Long id, TaskDto taskDto);
    public void markAsDone(long id);

}
