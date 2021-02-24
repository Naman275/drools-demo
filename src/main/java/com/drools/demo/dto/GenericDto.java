package com.drools.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericDto {
    private Map<String, Object> fields;
    private List<PersonDto> personList;
}
