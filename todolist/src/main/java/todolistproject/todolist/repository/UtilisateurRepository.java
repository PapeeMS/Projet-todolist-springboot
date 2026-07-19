package todolistproject.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolistproject.todolist.entity.Utilisateur;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);

    boolean existsByUsername(String username);
}
