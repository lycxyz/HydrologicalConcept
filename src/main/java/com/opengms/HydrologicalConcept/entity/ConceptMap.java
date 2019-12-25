package com.opengms.HydrologicalConcept.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(value = "概念图对象：ConceptMap")
@Data
public class ConceptMap {
    String geoId;
    String name;
    String description;
    String xml;
    String pathUrl;
    String mapClass;

    String shapeInfo;
    String spacePositions;
    ConceptStructure concept;
    List<PropertyStructure> properties;
    List<ProcessStructure> processes;
    List<ElementRelationStructure> elementRelations;
}
