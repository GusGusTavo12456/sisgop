package pe.gob.mininter.sisgop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.mininter.sisgop.model.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
