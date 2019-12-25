package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProcessStructure {
    String name;
    List<String> elements;
    String description;
}
