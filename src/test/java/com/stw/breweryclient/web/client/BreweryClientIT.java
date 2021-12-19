package com.stw.breweryclient.web.client;

import com.stw.brewery.web.model.BeerDto;
import java.net.URI;
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
    
     /**
     * Test of newBeer method, of class BreweryClient.
     */
    @Test
    public void testNewBeer() {
        BeerDto dto= BeerDto.builder().beerName("New Beer").build();
        
        URI uri = client.saveBeer(dto);
        
        assertNotNull(uri);
        
        System.out.println(uri.toString());
    }

    @Test
    public void testUpdateBeer() {
        BeerDto dto= BeerDto.builder().beerName("New Beer").build();
        
        client.updateBeer(UUID.randomUUID(), dto);
    }
    
}
