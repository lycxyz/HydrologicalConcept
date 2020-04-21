package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Type_Shape;
import lombok.Data;

import java.util.List;

@Data
public class Rule_Shape {
    String id;
    String from;
    List<String> to;
    Type_Shape type;
    String description;
}
