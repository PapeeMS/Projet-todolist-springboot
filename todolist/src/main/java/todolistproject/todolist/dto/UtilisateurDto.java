package todolistproject.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurDto {
    private Long id;
    private String username;
    private String password;
}
