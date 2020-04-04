package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Type_Concept;
import lombok.Data;

import java.util.List;

@Data
public class Rule_Concept {
    String id;
    String from;
    List<String> to;
    Type_Concept type;
    String description;
}
