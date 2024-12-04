package com.indra.vehicles_api.model.manufacturer;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Declares class to hold car manufacturer information.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {

    @Id
    private Integer code;
    private String name;

}
