package uni.isw.sigconbackend.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.dto.PersonaRequest;
import uni.isw.sigconbackend.dto.PersonaResponse;
import uni.isw.sigconbackend.dto.SolicitanteRequest;
import uni.isw.sigconbackend.dto.SolicitanteResponse;
import uni.isw.sigconbackend.service.PersonaService;
import uni.isw.sigconbackend.service.SolicitanteService;
import uni.isw.sigconbackend.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/v1/solicitante")
public class SolicitanteController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    SolicitanteService solicitanteService;  
    
    @Autowired
    PersonaService personaService;
    
    @GetMapping()
    public ResponseEntity<?> getSolicitante(){
        List<SolicitanteResponse> listaSolicitanteResponse=null;
        try{
            listaSolicitanteResponse=solicitanteService.listSolicitante();
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaSolicitanteResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Solicitante not found").build());           
        return ResponseEntity.ok(listaSolicitanteResponse);        
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findSolicitanteById(@RequestBody Optional<SolicitanteRequest> solicitanteRequest){
        logger.info(">find" +  solicitanteRequest.toString());
        SolicitanteResponse solicitanteResponse;
        try{
            solicitanteResponse=solicitanteService.findSolicitante(solicitanteRequest.get().getIdPersona());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitanteResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("solicitante not found").build());           
        return ResponseEntity.ok(solicitanteResponse);        
        
    }
    @GetMapping("/findByPersonaIdPersona")
    public ResponseEntity<?> findByPersonaIdPersona(@RequestBody SolicitanteRequest solicitanteRequest){
        logger.info(">findByIdPersona" +  solicitanteRequest.toString());
        SolicitanteResponse solicitanteResponse;
        try{
            solicitanteResponse=solicitanteService.findByPersonaIdPersona(solicitanteRequest.getIdPersona());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitanteResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("findByIdPersona not found").build());           
        return ResponseEntity.ok(solicitanteResponse);        
        
    }
    
    @GetMapping("/findByDni/{ndocumento}")
    public ResponseEntity<?> findByDni(@PathVariable String ndocumento){
        logger.info(">findByDni" + ndocumento);
        if(ndocumento==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("findByDni not found").build());           
        
        PersonaResponse personaResponse=personaService.findByNdocumento(ndocumento);        
        if (personaResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("findByDni not found").build());           
        
        SolicitanteResponse solicitanteResponse;
        try{
            solicitanteResponse=solicitanteService.findByPersonaIdPersona(personaResponse.getIdPersona());            
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitanteResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("3findBySolicitantePersonaIdPersona not found").build());           
        return ResponseEntity.ok(solicitanteResponse);        
        
    }
    
    @PostMapping()
    public ResponseEntity<?> insertSolicitante(@RequestBody SolicitanteRequest solicitanteRequest){
        logger.info(">insert " +  solicitanteRequest.toString());
        SolicitanteResponse solicitanteResponse;
        try{            
            solicitanteResponse=(SolicitanteResponse)solicitanteService.insertSolicitante(solicitanteRequest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitanteResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("solicitante not insert").build());           
        return ResponseEntity.ok(solicitanteResponse);        
    }    
    
    @PutMapping()
    public ResponseEntity<?> updateSolicitante(@RequestBody SolicitanteRequest solicitanteRequest){
        logger.info(">update" +  solicitanteRequest.toString());
        SolicitanteResponse solicitanteResponse;
        try{
            solicitanteResponse=solicitanteService.findSolicitante(solicitanteRequest.getIdSolicitante());
            if (solicitanteResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("solicitante not found").build());           
            
            solicitanteResponse=solicitanteService.updateSolicitante(solicitanteRequest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitanteResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("solicitante not update").build());           
        return ResponseEntity.ok(solicitanteResponse);  
    }    
    
    @DeleteMapping()
    public ResponseEntity<?> deleteSolicitante(@RequestBody Optional<SolicitanteRequest> solicitanteRequest){
        logger.info(">delete" +  solicitanteRequest.toString());
        SolicitanteResponse solicitanteResponse;
        try{            
            solicitanteResponse=solicitanteService.findSolicitante(solicitanteRequest.get().getIdPersona());
            if(!solicitanteRequest.isPresent())                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("solicitante not found for delete").build());  
            solicitanteService.deleteSolicitante(solicitanteRequest.get().getIdSolicitante());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        return ResponseEntity.ok(solicitanteResponse);  
    }
    
}
