package com.opengms.HydrologicalConcept.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel(value = "概念图对象：ConceptMap")
@Data
public class ConceptMap{
    String id;
    String geoId;
    String conceptId;
    String name;

    String description;
    String graphXml;
    String xml;
    String pathUrl;
    String mapClass;
    String[] tags;

    ShapeStructure shapeInfo;
    SpacePositionStructure spacePosition;
    List<ConceptStructure> concept;
    List<PropertyStructure> properties;
    List<ProcessStructure> processes;
    List<ElementRelationStructure> elementRelations;

    Integer width;
    Integer height;
}
