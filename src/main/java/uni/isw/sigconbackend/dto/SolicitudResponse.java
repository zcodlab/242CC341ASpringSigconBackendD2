package uni.isw.sigconbackend.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uni.isw.sigconbackend.model.Predio;
import uni.isw.sigconbackend.model.Servicio;
import uni.isw.sigconbackend.model.Solicitante;
import uni.isw.sigconbackend.model.Solicitud;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudResponse {
    private Long idSolicitud;            
    private double areaPredio;
    private Integer numCasas;
    private Integer cantAcomunes;
    private Integer areaAcomunes;
    private Integer cantVigilantes;
    private Integer cantPlimpieza;
    private Integer cantAdministracion;
    private Integer cantJardineria;
    private Date fechaSolicitud;    
    private Predio predio;
    private Solicitante solicitante;
    private Servicio servicio;
    
    public static SolicitudResponse fromEntity(Solicitud solicitud) {
        return SolicitudResponse.builder()
                .idSolicitud(solicitud.getIdSolicitud())                             
                .areaPredio(solicitud.getAreaPredio())                             
                .numCasas(solicitud.getNumCasas())                             
                .cantAcomunes(solicitud.getCantAcomunes())                             
                .areaAcomunes(solicitud.getAreaAcomunes())                             
                .cantVigilantes(solicitud.getCantVigilantes())                             
                .cantPlimpieza(solicitud.getCantPlimpieza())                             
                .cantAdministracion(solicitud.getCantAdministracion())                             
                .cantJardineria(solicitud.getCantJardineria())                             
                .fechaSolicitud(solicitud.getFechaSolicitud())                             
                .predio(solicitud.getPredio())                             
                .solicitante(solicitud.getSolicitante())                             
                .servicio(solicitud.getServicio())                             
                .build();
    }
    
    public static List<SolicitudResponse> fromEntities(List<Solicitud> solicitud) {
        return solicitud.stream()
                .map(SolicitudResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
