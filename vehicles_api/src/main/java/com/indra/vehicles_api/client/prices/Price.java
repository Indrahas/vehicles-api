package com.indra.vehicles_api.client.prices;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Data
@NoArgsConstructor
public class Price {

    private String currency;
    private BigDecimal price;
    private Long vehicleId;

}
