package uni.isw.sigconbackend.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.dto.SolicitanteResponse;
import uni.isw.sigconbackend.dto.SolicitudRequest;
import uni.isw.sigconbackend.dto.SolicitudResponse;
import uni.isw.sigconbackend.model.Predio;
import uni.isw.sigconbackend.model.Servicio;
import uni.isw.sigconbackend.model.Solicitante;
import uni.isw.sigconbackend.model.Solicitud;
import uni.isw.sigconbackend.repository.PredioRepository;
import uni.isw.sigconbackend.repository.ServicioRepository;
import uni.isw.sigconbackend.repository.SolicitanteRepository;
import uni.isw.sigconbackend.repository.SolicitudRepository;

@Service
public class SolicitudService {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    PredioRepository predioRepository;
    @Autowired
    SolicitanteRepository solicitanteRepository;
    @Autowired
    ServicioRepository servicioRepository;
    
    public List<SolicitudResponse> listSolicitudes(){        
        return SolicitudResponse.fromEntities((List<Solicitud>)solicitudRepository.findAll());
    }
    
    public Optional<Solicitud> getSolicitud(Long id){
        return solicitudRepository.findById(id);
    }
    
    public SolicitudResponse findSolicitud(Long id){
        return SolicitudResponse.fromEntity(solicitudRepository.findById(id).get());                
    }
     
    public SolicitudResponse insertSolicitud(SolicitudRequest solicitudRequest){        
        Long idPredio=solicitudRequest.getIdPredio();
        Predio predio=predioRepository.findById(idPredio).get();
        logger.info(">insertSolicitud-predio" +  predio.toString());
        if (predio==null) return new SolicitudResponse();
        
        Long idSolicitante=solicitudRequest.getIdSolicitante();
        Solicitante solicitante=solicitanteRepository.findById(idSolicitante).get();
        logger.info(">insertSolicitud-solicitante" +  solicitante.toString());
        if (solicitante==null) return new SolicitudResponse();
        
        Long idServicio=solicitudRequest.getIdServicio();
        Servicio servicio=servicioRepository.findById(idServicio).get();
        logger.info(">insertSolicitud-servicio" +  servicio.toString());
        if (servicio==null) return new SolicitudResponse();
        
        Solicitud solicitud=new Solicitud(
                solicitudRequest.getIdSolicitud(),
                solicitudRequest.getAreaPredio(),
                solicitudRequest.getNumCasas(),
                solicitudRequest.getCantAcomunes(),                
                solicitudRequest.getAreaAcomunes(),
                solicitudRequest.getCantVigilantes(),
                solicitudRequest.getCantPlimpieza(),
                solicitudRequest.getCantAdministracion(),
                solicitudRequest.getCantJardineria(),
                solicitudRequest.getFechaSolicitud(),
                predio,               
                solicitante,
                servicio);
        solicitud=solicitudRepository.save(solicitud);
        SolicitudResponse solicitudResponse=SolicitudResponse.fromEntity(solicitud);
        return solicitudResponse;
    } 
    
    public SolicitudResponse updateSolicitud(SolicitudRequest solicitudRequest){        
        Long idSolicitud=solicitudRequest.getIdSolicitud();
        Solicitud solicitud=solicitudRepository.findById(idSolicitud).get();
        logger.info(">updateSolicitud" +  solicitudRequest.toString());
        if (solicitud==null) return new SolicitudResponse();
        
        Long idPredio=solicitudRequest.getIdPredio();
        Predio predio=predioRepository.findById(idPredio).get();
        logger.info(">insertSolicitud-predio" +  predio.toString());
        if (predio==null) return new SolicitudResponse();
        
        Long idSolicitante=solicitudRequest.getIdSolicitante();
        Solicitante solicitante=solicitanteRepository.findById(idSolicitante).get();
        logger.info(">insertPersona-solicitante" +  solicitante.toString());
        if (solicitante==null) return new SolicitudResponse();
        
        Long idServicio=solicitudRequest.getIdServicio();
        Servicio servicio=servicioRepository.findById(idServicio).get();
        logger.info(">insertPersona-servicio" +  servicio.toString());
        if (servicio==null) return new SolicitudResponse();
        
        Solicitud newSolicitud=new Solicitud(
                solicitudRequest.getIdSolicitud(),
                solicitudRequest.getAreaPredio(),
                solicitudRequest.getNumCasas(),
                solicitudRequest.getCantAcomunes(),                
                solicitudRequest.getAreaAcomunes(),
                solicitudRequest.getCantVigilantes(),
                solicitudRequest.getCantPlimpieza(),
                solicitudRequest.getCantAdministracion(),
                solicitudRequest.getCantJardineria(),
                solicitudRequest.getFechaSolicitud(),
                predio,               
                solicitante,
                servicio);
        newSolicitud=solicitudRepository.save(newSolicitud);
        SolicitudResponse solicitudResponse=SolicitudResponse.fromEntity(newSolicitud);
        return solicitudResponse;
    }   
    
    public void deleteSolicitud(Long id){
        solicitudRepository.deleteById(id);
    } 
}
