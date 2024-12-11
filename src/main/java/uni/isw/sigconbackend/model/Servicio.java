package uni.isw.sigconbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="servicio")
public class Servicio {   
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_servicio",nullable=false)
    private Long idServicio;
    private String descripcion;
    private double precio;
    
}
