package uni.isw.sigconbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="tipo_predio")
public class TipoPredio {
    @Id    
    @Column(name="id_tipo_predio",nullable=false)
    private Integer idTipoPredio;
    @Column(name="nomre_predio",nullable=false)
    private String descripcion;    
}
