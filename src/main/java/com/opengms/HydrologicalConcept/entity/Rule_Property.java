package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Type_Property;
import lombok.Data;

import java.util.List;

@Data
public class Rule_Property {
    String id;
    String from;
    List<String> to;
    Type_Property type;
    String description;
}
