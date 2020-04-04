package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Type_ElementRelation;
import lombok.Data;

import java.util.List;

@Data
public class Rule_ElementRelation {
    String id;
    String from;
    List<String> to;
    Type_ElementRelation type;
    String description;
}
