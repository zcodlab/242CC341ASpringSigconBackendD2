package uni.isw.sigconbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitanteRequest {
    private Long idSolicitante;            
    private Long telefono;
    private String correo;
    private Long idPersona; 
    private Long idRol; 
}
