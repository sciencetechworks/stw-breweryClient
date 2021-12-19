
package com.stw.breweryclient.web.client;

import com.stw.brewery.web.model.BeerDto;
import com.stw.brewery.web.model.CustomerDto;
import java.net.URI;
import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Component
@ConfigurationProperties(value="stw.brewery", ignoreInvalidFields = false)
public class BreweryClient {
    public final String BEER_PATH_V1="/api/v1/beer/";
    public final String CUSTOMER_PATH_V1="/api/v1/customer";
    
    private String apihost;
    private final RestTemplate restTemplate;
    
    public BreweryClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate=restTemplateBuilder.build();
    }
    
    public BeerDto getBeerById(UUID uuid){
        return restTemplate.getForObject(apihost+BEER_PATH_V1+uuid.toString(),
                BeerDto.class);
    }
    
    public URI saveBeer(BeerDto beerDto){
        return restTemplate.postForLocation(apihost+BEER_PATH_V1,beerDto);
    }
    
    public void updateBeer(UUID uuid,BeerDto beerDto)
    {
        restTemplate.put(apihost+BEER_PATH_V1+uuid.toString(),beerDto);
    }
    
    public void deleteBeer(UUID uuid)
    {
        restTemplate.delete(apihost+BEER_PATH_V1+uuid.toString());
    }
    
    // CUSTOMER
    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apihost+CUSTOMER_PATH_V1+uuid.toString(),
                CustomerDto.class);
    }
    
    public URI saveCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apihost+CUSTOMER_PATH_V1,customerDto);
    }
    
    public void updateCustomer(UUID uuid,CustomerDto customerDto)
    {
        restTemplate.put(apihost+CUSTOMER_PATH_V1+uuid.toString(),customerDto);
    }
    
    public void deleteCustomer(UUID uuid)
    {
        restTemplate.delete(apihost+CUSTOMER_PATH_V1+uuid.toString());
    }
    
    
    /**
     * @param apihost the apihost to set
     */
    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
