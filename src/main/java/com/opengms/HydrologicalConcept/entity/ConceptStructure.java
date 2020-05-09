package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class ConceptStructure {
    String definition;
    String source;
    String[] tags;
    List<String> relatedConcepts;
    List<ConceptClassification> classifications;
    List<UserImage> relateImages;
}
