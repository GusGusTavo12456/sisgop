package pe.gob.mininter.sisgop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.mininter.sisgop.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
