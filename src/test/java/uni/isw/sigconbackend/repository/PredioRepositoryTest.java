/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package uni.isw.sigconbackend.repository;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uni.isw.sigconbackend.model.Predio;
import uni.isw.sigconbackend.model.TipoDocumento;
import uni.isw.sigconbackend.model.TipoPredio;
import uni.isw.sigconbackend.model.Ubigeo;

/**
 *
 * @author zemr
 */
@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class PredioRepositoryTest {
    @Autowired
    PredioRepository predioRepository;
    
    @Autowired
    TipoPredioRepository tipoPredioRepository;
    
    @Autowired
    UbigeoRepository ubigeoRepository;    
    
    @Test
    public void testPredio() {
        TipoPredio tipoPredio=TipoPredio.builder()
                .idTipoPredio(1)
                .descripcion("Condominio")
                .build();               
        tipoPredio=tipoPredioRepository.save(tipoPredio);
                
        Ubigeo ubigeo=Ubigeo.builder()
                .idUbigeo("150114")
                .departamento("Lima")
                .provincia("Lima")
                .distrito("La Molina").build();               
        ubigeo=ubigeoRepository.save(ubigeo);
        
        Predio predio=Predio.builder()
                .descripcion("Las Golondrinas")
                .ruc("123456789")
                .direccion("Av. Javier Prado 123")
                .correo("admin@golondrinas.com")
                .telefono("369963212")
                .totalMdu(30)
                .tipoPredio(tipoPredio)
                .ubigeo(ubigeo)
                .build();
        predio=predioRepository.save(predio);
        
        List<Predio> predioList = (List<Predio>)predioRepository.findAll();
      
        Assertions.assertThat(predioList).isNotNull();
        Assertions.assertThat(predioList.size()).isEqualTo(1);  
        
    }
    
    
}
