package com.drools.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse {

    private GenericDto genericDto;

    private Map<String,Boolean> rulesExecuted;

    private Integer totalRules;

    private Integer totalPassed;

    private Boolean overallStatus;

    private String ruleVersion;

    @DateTimeFormat
    private Date createdAt=new Date();
    @DateTimeFormat
    private Date updatedAt=new Date();

}
