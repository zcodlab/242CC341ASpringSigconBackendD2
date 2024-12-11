package uni.isw.sigconbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.sigconbackend.model.Solicitante;
@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante,Long> {
    Solicitante findByPersonaIdPersona(Long idPersona);
}
