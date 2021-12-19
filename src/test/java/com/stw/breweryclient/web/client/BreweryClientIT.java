package com.stw.breweryclient.web.client;

import com.stw.brewery.web.model.BeerDto;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Usuario
 */
@SpringBootTest
public class BreweryClientIT {
    
    @Autowired
    BreweryClient client;
    
    public BreweryClientIT() {
    }

    /**
     * Test of getBeerById method, of class BreweryClient.
     */
    @Test
    public void testGetBeerById() {
        BeerDto dto= client.getBeerById(UUID.randomUUID());
        assertNotNull(dto);
    }

   
    
}
