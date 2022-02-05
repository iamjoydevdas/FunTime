package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class City 
{
    private String shortName;
    private String name;
    private String favCity;
    private Boolean active;
    private String state;
    private Long pin;
}
