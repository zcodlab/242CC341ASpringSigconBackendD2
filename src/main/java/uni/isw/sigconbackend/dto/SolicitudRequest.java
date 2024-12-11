package uni.isw.sigconbackend.dto;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudRequest {
    private Long idSolicitud;            
    private Long idPredio;
    private Long idSolicitante;
    private Long idServicio;
    private double areaPredio;
    private Integer numCasas;
    private Integer cantAcomunes;
    private Integer areaAcomunes;
    private Integer cantVigilantes;
    private Integer cantPlimpieza;
    private Integer cantAdministracion;
    private Integer cantJardineria;
    private Date fechaSolicitud;
}
