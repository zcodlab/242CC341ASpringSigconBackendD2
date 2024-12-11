package uni.isw.sigconbackend.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.dto.SolicitanteRequest;
import uni.isw.sigconbackend.dto.SolicitanteResponse;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.model.Rol;
import uni.isw.sigconbackend.model.Solicitante;
import uni.isw.sigconbackend.repository.PersonaRepository;
import uni.isw.sigconbackend.repository.RolRepository;
import uni.isw.sigconbackend.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    SolicitanteRepository solicitanteRepository;
    
    @Autowired
    PersonaRepository personaRepository;
    
    @Autowired
    RolRepository rolRepository;
    
    public List<SolicitanteResponse> listSolicitante(){        
        return SolicitanteResponse.fromEntities((List<Solicitante>) solicitanteRepository.findAll());
    }
    public SolicitanteResponse findSolicitante(Long id){
        return SolicitanteResponse.fromEntity(solicitanteRepository.findById(id).get());                
    }
    
    public SolicitanteResponse findByPersonaIdPersona(Long id){
        return SolicitanteResponse.fromEntity(solicitanteRepository.findByPersonaIdPersona(id));
    }
    public SolicitanteResponse insertSolicitante(SolicitanteRequest solicitanteRequest){                
        Long idPersona=solicitanteRequest.getIdPersona();
        Persona persona=personaRepository.findById(idPersona).get();
        logger.info(">insertSolicitante-persona" +  persona.toString());
        if (persona==null) return new SolicitanteResponse();
        
        Long idRol=solicitanteRequest.getIdRol();
        Rol rol=rolRepository.findById(idRol).get();
        logger.info(">insertSolicitante-rol" +  rol.toString());
        if (rol==null) return new SolicitanteResponse();
        
        Solicitante solicitante=new Solicitante(
                solicitanteRequest.getIdSolicitante(),                
                solicitanteRequest.getTelefono(),
                solicitanteRequest.getCorreo(),
                persona,                                
                rol);
        solicitante=solicitanteRepository.save(solicitante);        
        SolicitanteResponse solicitanteResponse=SolicitanteResponse.fromEntity(solicitante);        
        return solicitanteResponse;
    } 
    
    public SolicitanteResponse updateSolicitante(SolicitanteRequest solicitanteRequest){   
        Long idSolicitante=solicitanteRequest.getIdSolicitante();
        Solicitante solicitante=solicitanteRepository.findById(idSolicitante).get();
        logger.info(">updateSolicitante-solicitante" +  solicitante.toString());
        if (solicitante==null) return new SolicitanteResponse();
                
        Long idPersona=solicitanteRequest.getIdPersona();
        Persona persona=personaRepository.findById(idPersona).get();
        logger.info(">insertSolicitante-persona" +  persona.toString());
        if (persona==null) return new SolicitanteResponse();
        
        Long idRol=solicitanteRequest.getIdRol();
        Rol rol=rolRepository.findById(idRol).get();
        logger.info(">insertSolicitante-rol" +  rol.toString());
        if (rol==null) return new SolicitanteResponse();
        
        Solicitante newSolicitante=new Solicitante(
                solicitanteRequest.getIdSolicitante(),                
                solicitanteRequest.getTelefono(),
                solicitanteRequest.getCorreo(),
                persona,                                
                rol);
        solicitante=solicitanteRepository.save(solicitante);        
        SolicitanteResponse solicitanteResponse=SolicitanteResponse.fromEntity(newSolicitante);        
        return solicitanteResponse;
    }   
    
    public void deleteSolicitante(Long id){
        solicitanteRepository.deleteById(id);
    }
    
}
