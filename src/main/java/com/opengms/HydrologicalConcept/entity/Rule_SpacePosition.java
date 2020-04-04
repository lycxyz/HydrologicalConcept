package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Type_SpacePosition;
import lombok.Data;

import java.util.List;

@Data
public class Rule_SpacePosition {
    String id;
    String from;
    List<String> to;
    Type_SpacePosition type;
    String description;
}
