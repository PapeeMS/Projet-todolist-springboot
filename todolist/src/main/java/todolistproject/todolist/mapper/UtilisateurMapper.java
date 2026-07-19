package todolistproject.todolist.mapper;

import org.mapstruct.Mapper;
import todolistproject.todolist.dto.UtilisateurDto;
import todolistproject.todolist.entity.Utilisateur;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDto toUtilisateurDto(Utilisateur utilisateur);

    Utilisateur toUtilisateur(UtilisateurDto utilisateurDto);

    List<UtilisateurDto> toUtilisateurDtos(List<Utilisateur> utilisateurs);

    List<Utilisateur> toUtilisateurs(List<UtilisateurDto> utilisateurDtos);
}
