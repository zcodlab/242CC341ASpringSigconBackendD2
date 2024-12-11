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
import uni.isw.sigconbackend.model.TipoDocumento;

/**
 *
 * @author zemr
 */
@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
public class TipoDocumentoRepositoryTest {
    @Autowired
    TipoDocumentoRepository tipoDocumentoRepository;    
    
    private TipoDocumento tipoDocumento;
    
    public TipoDocumentoRepositoryTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testSomeMethod() {
        tipoDocumento=TipoDocumento.builder()
                .idTipoDocumento(1)
                .descripcion("DNI").build();        
        tipoDocumento=tipoDocumentoRepository.save(tipoDocumento);
        List<TipoDocumento> tipoDocumentoList = (List<TipoDocumento>)tipoDocumentoRepository.findAll();
      
        Assertions.assertThat(tipoDocumentoList).isNotNull();
        Assertions.assertThat(tipoDocumentoList.size()).isEqualTo(1);  
    }
    
}
