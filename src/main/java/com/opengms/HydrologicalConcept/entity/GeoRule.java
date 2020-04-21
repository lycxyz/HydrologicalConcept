package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Aspect;
import lombok.Data;
import java.util.List;

@Data
public class GeoRule {
    String from;
    List<String> to;
    String type;
    String description;
    Aspect aspect;
}
