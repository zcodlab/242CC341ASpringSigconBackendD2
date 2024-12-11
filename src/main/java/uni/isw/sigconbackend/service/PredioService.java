package uni.isw.sigconbackend.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.isw.sigconbackend.dto.PredioRequest;
import uni.isw.sigconbackend.dto.PredioResponse;
import uni.isw.sigconbackend.model.Predio;
import uni.isw.sigconbackend.model.TipoPredio;
import uni.isw.sigconbackend.model.Ubigeo;
import uni.isw.sigconbackend.repository.PredioRepository;
import uni.isw.sigconbackend.repository.TipoPredioRepository;
import uni.isw.sigconbackend.repository.UbigeoRepository;

@Service
public class PredioService {
    private final Logger logger=LoggerFactory.getLogger(this.getClass());
    @Autowired
    PredioRepository predioRepository;
    
    @Autowired
    TipoPredioRepository tipoPredioRepository;
    
    @Autowired
    UbigeoRepository ubigeoRepository;
    
    public List<PredioResponse> listPredio(){        
        return PredioResponse.fromEntities((List<Predio>) predioRepository.findAll());
    }
    public PredioResponse findPredio(Long id){
        return PredioResponse.fromEntity(predioRepository.findById(id).get());                
    }
    
    public PredioResponse insertPredio(PredioRequest predioRequest){                
        Integer idTipoPredio=predioRequest.getIdTipoPredio();
        TipoPredio tipoPredio=tipoPredioRepository.findById(idTipoPredio).get();
        logger.info(">insertPredio-tipoPredio" +  tipoPredio.toString());
        if (tipoPredio==null) return new PredioResponse();
        
        String idUbigeo=predioRequest.getIdUbigeo();
        Ubigeo ubigeo=ubigeoRepository.findById(idUbigeo).get();
        logger.info(">insertPredio-ubigeo" +  ubigeo.toString());
        if (ubigeo==null) return new PredioResponse();
        
        Predio predio=new Predio(
                predioRequest.getIdPredio(),
                predioRequest.getDescripcion(),
                predioRequest.getCorreo(),
                predioRequest.getRuc(),
                predioRequest.getTelefono(),                
                predioRequest.getDireccion(),                
                predioRequest.getTotalMdu(),
                tipoPredio,
                ubigeo);
        predio=predioRepository.save(predio);        
        PredioResponse predioResponse=PredioResponse.fromEntity(predio);        
        return predioResponse;
    } 
    
    public PredioResponse updatePredio(PredioRequest predioRequest){        
        Long idPredio=predioRequest.getIdPredio();
        Predio predio=predioRepository.findById(idPredio).get();        
        if (predio==null) return new PredioResponse();
        
        Integer idTipoPredio=predioRequest.getIdTipoPredio();
        TipoPredio tipoPredio=tipoPredioRepository.findById(idTipoPredio).get();
        logger.info(">insertPredio-tipoPredio" +  tipoPredio.toString());
        if (tipoPredio==null) return new PredioResponse();
        
        String idUbigeo=predioRequest.getIdUbigeo();
        Ubigeo ubigeo=ubigeoRepository.findById(idUbigeo).get();
        if (ubigeo==null) return new PredioResponse();
        
        Predio newpredio=new Predio(
                predioRequest.getIdPredio(),
                predioRequest.getDescripcion(),
                predioRequest.getCorreo(),
                predioRequest.getRuc(),
                predioRequest.getTelefono(),                
                predioRequest.getDireccion(),                
                predioRequest.getTotalMdu(),
                tipoPredio,
                ubigeo);
        predio=predioRepository.save(predio);        
        PredioResponse predioResponse=PredioResponse.fromEntity(newpredio);        
        return predioResponse;
    }   
    
    public void deletePredio(Long id){
        predioRepository.deleteById(id);
    }
    
}
