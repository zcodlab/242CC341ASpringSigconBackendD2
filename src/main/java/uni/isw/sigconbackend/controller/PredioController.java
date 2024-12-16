package uni.isw.sigconbackend.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.isw.sigconbackend.dto.PredioRequest;
import uni.isw.sigconbackend.dto.PredioResponse;
import uni.isw.sigconbackend.service.PredioService;
import uni.isw.sigconbackend.utils.ErrorResponse;

@RestController
@RequestMapping(path="api/v1/predio")
public class PredioController {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PredioService predioService;   
    
    @GetMapping()
    public ResponseEntity<?> getPredio(){
        List<PredioResponse> listaPredioResponse=null;
        try{
            listaPredioResponse=predioService.listPredio();
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (listaPredioResponse.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio not found").build());           
        return ResponseEntity.ok(listaPredioResponse);        
    }
    
    @GetMapping("/find")
    public ResponseEntity<?> findPredioById(@RequestBody Optional<PredioRequest> predioRequest){
        logger.info(">find" +  predioRequest.toString());
        PredioResponse predioResponse;
        try{
            predioResponse=predioService.findPredio(predioRequest.get().getIdPredio());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (predioResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio not found").build());           
        return ResponseEntity.ok(predioResponse);        
        
    }
    
    @PostMapping()
    public ResponseEntity<?> insertPredio(@RequestBody PredioRequest predioRequest){
        logger.info(">insert " +  predioRequest.toString());
        PredioResponse predioResponse;
        try{            
            predioResponse=predioService.insertPredio(predioRequest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (predioResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio not insert").build());           
        return ResponseEntity.ok(predioResponse);        
    }   
    
    @PutMapping()
    public ResponseEntity<?> updatePredio(@RequestBody PredioRequest predioRequest){
        logger.info(">updatePredio" +  predioRequest.toString());
        PredioResponse predioResponse;
        try{
            predioResponse=predioService.findPredio(predioRequest.getIdPredio());
            if (predioResponse==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio not found").build());           
            
            predioResponse=predioService.updatePredio(predioRequest);
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (predioResponse==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio not update").build());           
        return ResponseEntity.ok(predioResponse);  
    }    
    
    @DeleteMapping()
    public ResponseEntity<?> deletePredio(@RequestBody Optional<PredioRequest> predioRequest){
        logger.info(">delete" +  predioRequest.toString());
        PredioResponse predioResponse;
        try{            
            predioResponse=predioService.findPredio(predioRequest.get().getIdPredio());
            if(!predioRequest.isPresent())                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio not found for delete").build());  
            predioService.deletePredio(predioRequest.get().getIdPredio());
        }catch(Exception e){
            logger.error("Error inesperado", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        return ResponseEntity.ok(predioResponse);  
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<?> findPredioByRuc(@PathVariable String ruc) {
        PredioResponse predioResponse;
        try {
            predioResponse=predioService.findPredioByRuc(ruc);
        } catch (Exception e) {
            logger.error("error in findPredioByRuc:", e);
            predioResponse = null;
        }

        if(predioResponse == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder().message("Predio con Ruc: " + ruc + " no encontrado").build());

        return ResponseEntity.ok(predioResponse);
    }
    
}
