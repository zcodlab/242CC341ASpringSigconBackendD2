package uni.isw.sigconbackend.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.isw.sigconbackend.model.Persona;
import uni.isw.sigconbackend.model.Rol;
import uni.isw.sigconbackend.model.Solicitante;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitanteResponse {    
    private Long idSolicitante;            
    private Long telefono;
    private String correo;
    private Persona persona; 
    private Rol rol; 
    
    public static SolicitanteResponse fromEntity(Solicitante solicitante) {
        return SolicitanteResponse.builder()
                .idSolicitante(solicitante.getIdSolicitante())
                .telefono(solicitante.getTelefono())
                .correo(solicitante.getCorreo())
                .persona(solicitante.getPersona())
                .rol(solicitante.getRol())                
                .build();
    }
    
    public static List<SolicitanteResponse> fromEntities(List<Solicitante> solicitante) {
        return solicitante.stream()
                .map(SolicitanteResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
}
