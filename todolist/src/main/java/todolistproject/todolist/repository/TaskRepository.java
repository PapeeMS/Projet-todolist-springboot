package todolistproject.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolistproject.todolist.entity.Task;
import todolistproject.todolist.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUtilisateur(Utilisateur utilisateur);

    Optional<Task> findByIdAndUtilisateur(Long id, Utilisateur utilisateur);
}
