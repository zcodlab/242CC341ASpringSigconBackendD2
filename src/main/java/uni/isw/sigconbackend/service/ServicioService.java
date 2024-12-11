package uni.isw.sigconbackend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.model.Servicio;
import uni.isw.sigconbackend.repository.ServicioRepository;

@Service
public class ServicioService {
    @Autowired
    ServicioRepository servicioRepository;
    
    public List<Servicio> getServicios(){
        return servicioRepository.findAll();
    }
    public Optional<Servicio> getServicio(Long id){
        return servicioRepository.findById(id);
    }
    
    public void insertServicio(Servicio servicio){
        servicioRepository.save(servicio);
    }   
    
    public void updateServicio(Servicio servicio){
        servicioRepository.save(servicio);
    }   
    
    public void deleteServicio(Long id){
        servicioRepository.deleteById(id);
    }
    
}
