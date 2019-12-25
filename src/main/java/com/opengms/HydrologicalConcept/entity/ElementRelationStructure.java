package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class ElementRelationStructure {
    List<String> relateElements;
    String relateType;
    String relateValue;
}
