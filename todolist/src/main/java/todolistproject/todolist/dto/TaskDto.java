package todolistproject.todolist.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "taskDescription", columnDefinition = "TEXT")
    private String taskDescription;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "deadline",nullable = false)
    private LocalDateTime deadline;

    @Column(name="isDone")
    private Boolean isDone=false;

}
