package uni.isw.sigconbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PredioRequest {
   private Long idPredio;
   private String descripcion;
   private String ruc;
   private String telefono;
   private String correo;
   private String direccion;   
   private Integer totalMdu;
   private Integer idTipoPredio;
   private String idUbigeo; 
}
