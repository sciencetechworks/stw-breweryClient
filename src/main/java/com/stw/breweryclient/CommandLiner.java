
package com.stw.breweryclient;

import com.stw.brewery.web.model.BeerDto;
import com.stw.breweryclient.web.client.BreweryClient;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Component
public class CommandLiner implements CommandLineRunner {
  @Autowired
  BreweryClient client;

  @Override
  public void run(String... args) throws Exception {
    // Put your logic here
    BeerDto dto;
    try{
        dto= client.getBeerById(UUID.randomUUID());
    } catch(Exception iex){
        System.out.println("Is brewery server online?");
        return;
    }
      System.out.println("Beer:"+dto.toString());
  }

}
