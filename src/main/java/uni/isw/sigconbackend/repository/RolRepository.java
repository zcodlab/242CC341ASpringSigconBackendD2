package uni.isw.sigconbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.isw.sigconbackend.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
 
}
