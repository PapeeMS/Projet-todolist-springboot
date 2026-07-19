package todolistproject.todolist.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import todolistproject.todolist.dto.UtilisateurDto;
import todolistproject.todolist.entity.Utilisateur;
import todolistproject.todolist.mapper.UtilisateurMapper;
import todolistproject.todolist.repository.UtilisateurRepository;

@AllArgsConstructor
@Service
public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = findByUsername(username);

        return User.withUsername(utilisateur.getUsername())
                .password(utilisateur.getPassword())
                .roles("USER")
                .build();
    }

    public Utilisateur findByUsername(String username) {
        return utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable: " + username));
    }

    public boolean usernameExists(String username) {
        return utilisateurRepository.existsByUsername(username);
    }

    public UtilisateurDto register(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurMapper.toUtilisateur(utilisateurDto);
        utilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));
        return utilisateurMapper.toUtilisateurDto(utilisateurRepository.save(utilisateur));
    }
}
