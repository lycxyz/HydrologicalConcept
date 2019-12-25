package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class ConceptClassification {
    String depend;
    List<String> subConcepts;
}
