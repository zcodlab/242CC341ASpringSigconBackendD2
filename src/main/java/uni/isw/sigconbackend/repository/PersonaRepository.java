package uni.isw.sigconbackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.sigconbackend.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
    List<Persona> findBynDocumento(String dni);
}
