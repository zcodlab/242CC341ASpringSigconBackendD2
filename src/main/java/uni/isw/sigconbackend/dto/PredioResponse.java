package uni.isw.sigconbackend.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.isw.sigconbackend.model.Predio;
import uni.isw.sigconbackend.model.TipoPredio;
import uni.isw.sigconbackend.model.Ubigeo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PredioResponse {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long idPredio;
   private String descripcion;
   private String ruc;
   private String telefono;
   private String correo;
   private String direccion;   
   private Integer totalMdu;
   private TipoPredio tipoPredio;
   private Ubigeo ubigeo;    
   
   public static PredioResponse fromEntity(Predio predio) {
        return PredioResponse.builder()
                .idPredio(predio.getIdPredio())
                .descripcion(predio.getDescripcion())
                .ruc(predio.getRuc())
                .telefono(predio.getTelefono())
                .correo(predio.getCorreo())                
                .direccion(predio.getDireccion())            
                .totalMdu(predio.getTotalMdu())
                .tipoPredio(predio.getTipoPredio())
                .ubigeo(predio.getUbigeo()) //.provincia(persona.getUbigeo()==null ? "": persona.getUbigeo().getProvincia())
                .build();
    }
    
    public static List<PredioResponse> fromEntities(List<Predio> predio) {
        return predio.stream()
                .map(PredioResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
}
