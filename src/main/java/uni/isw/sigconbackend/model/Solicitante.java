package uni.isw.sigconbackend.model;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="solicitante")
public class Solicitante {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_solicitante",nullable=false)
    private Long idSolicitante;            
    private Long telefono;
    private String correo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")    
    private Persona persona; 
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")    
    private Rol rol; 
}
