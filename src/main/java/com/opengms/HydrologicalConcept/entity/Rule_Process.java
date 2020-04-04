package com.opengms.HydrologicalConcept.entity;

import com.opengms.HydrologicalConcept.entity.Rule_Enum.Type_Process;
import lombok.Data;

import java.util.List;

@Data
public class Rule_Process {
    String id;
    String from;
    List<String> to;
    Type_Process type;
    String description;
}
