package uni.isw.sigconbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.sigconbackend.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio,Long> {
    
}
