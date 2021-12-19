
package com.stw.brewery.web.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
 private UUID id;
 private String beerName;
 private String beerStyle;
 private Long upc;
}
