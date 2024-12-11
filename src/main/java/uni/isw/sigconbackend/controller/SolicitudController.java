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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.isw.sigconbackend.dto.SolicitudRequest;
import uni.isw.sigconbackend.dto.SolicitudResponse;
import uni.isw.sigconbackend.service.SolicitudService;
import uni.isw.sigconbackend.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/v1/solicitud")
public class SolicitudController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SolicitudService solicitudService;    
    
    @GetMapping()
    public ResponseEntity<?> getSolicitudes() {
            logger.info(">listar");

            List<SolicitudResponse> listaSolicitudes = null;
            try {
                    listaSolicitudes = solicitudService.listSolicitudes();
            } catch (Exception e) {
                    logger.error("Unexpected Exception caught.", e);
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (listaSolicitudes.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Solicitudes not found").build());           
        return ResponseEntity.ok(listaSolicitudes);    
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findSolicitudById(@RequestBody Optional<SolicitudRequest> solicitudRequest) {
        logger.info(">search" +  solicitudRequest.toString());
        SolicitudResponse solicitudResponse;
        try {
                solicitudResponse = solicitudService.findSolicitud(solicitudRequest.get().getIdSolicitud());                    

        } catch (Exception e) {
                logger.error("Unexpected Exception caught.", e);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitudResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Solicitud not found").build());           
        return ResponseEntity.ok(solicitudResponse);  
    }
    
    @PostMapping() 																		  
    public ResponseEntity<?> insertSolicitud(@RequestBody SolicitudRequest solicitudRequest){
        logger.info(">insertSolicitud: " + solicitudRequest.toString());        
        SolicitudResponse solicitudResponse;
        try{                  
            solicitudResponse=solicitudService.insertSolicitud(solicitudRequest);
        } catch(Exception e){
            logger.error("Unexpected Exception caught. "+ e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        if (solicitudResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Solicitud not insert").build());           
        return ResponseEntity.ok(solicitudResponse); 
    } 
    
    @PutMapping()
    public ResponseEntity<?> updateSolicitud(@RequestBody SolicitudRequest solicitudRequest){
        logger.info(">update" +  solicitudRequest.toString());
        SolicitudResponse solicitudResponse;
        try{
            solicitudResponse=solicitudService.findSolicitud(solicitudRequest.getIdSolicitud());
            if (solicitudResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found").build());           
            
            solicitudResponse=solicitudService.updateSolicitud(solicitudRequest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (solicitudResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not update").build());           
        return ResponseEntity.ok(solicitudResponse);  
    }    
    
    @DeleteMapping()
    public ResponseEntity<?> deleteSolicitud(@RequestBody Optional<SolicitudRequest> solicitudRequest){
        logger.info(">deleteSolicitud" +  solicitudRequest.toString());
        SolicitudResponse solicitudResponse;
        try{            
            solicitudResponse=solicitudService.findSolicitud(solicitudRequest.get().getIdSolicitud());
            if(!solicitudRequest.isPresent())                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Persona not found for delete").build());  
            solicitudService.deleteSolicitud(solicitudRequest.get().getIdSolicitud());                        
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        return ResponseEntity.ok(solicitudResponse);  
    }
   
   
}
