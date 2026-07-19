package todolistproject.todolist.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import todolistproject.todolist.dto.TaskDto;
import todolistproject.todolist.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toTaskDto(Task task);
    Task toTask(TaskDto taskDto);

    List<TaskDto> toTaskDtos(List<Task> tasks);
    List<Task> toTasks(List<TaskDto> taskDtos);

}
