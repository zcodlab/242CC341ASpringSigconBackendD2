package uni.isw.sigconbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="predio")
public class Predio {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id_predio",nullable=false)
   private Long idPredio;      
   private String descripcion;
   private String ruc;
   private String telefono;
   private String correo;
   private String direccion;   
   private Integer totalMdu;
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_tipo_predio", referencedColumnName = "id_tipo_predio")    
   private TipoPredio tipoPredio;   
   
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idubigeo", referencedColumnName = "idubigeo")    
   private Ubigeo ubigeo;   
}
