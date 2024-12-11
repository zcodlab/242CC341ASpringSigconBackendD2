package uni.isw.sigconbackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "id_predio", referencedColumnName = "id_predio")    
    private Predio predio;
    
    @ManyToOne
    @JoinColumn(name = "id_solicitante", referencedColumnName = "id_solicitante")    
    private Solicitante solicitante;
    
    @ManyToOne
    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio")    
    private Servicio servicio;
    
}
