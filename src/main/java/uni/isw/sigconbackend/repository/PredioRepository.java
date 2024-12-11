package uni.isw.sigconbackend.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uni.isw.sigconbackend.model.Predio;

@Repository
public interface PredioRepository extends CrudRepository<Predio,Long>{
    List<Predio> findByRuc(String ruc);
}
