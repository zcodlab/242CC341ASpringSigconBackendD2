package uni.isw.sigconbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uni.isw.sigconbackend.model.Solicitud;
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud,Long>{
}
