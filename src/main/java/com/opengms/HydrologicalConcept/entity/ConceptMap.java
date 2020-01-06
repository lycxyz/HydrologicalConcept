package com.opengms.HydrologicalConcept.entity;

import ch.qos.logback.core.pattern.SpacePadder;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@ApiModel(value = "概念图对象：ConceptMap")
@Data
public class ConceptMap{
    String _id;
    String geoId;
    String name;
    String description;
    String graphXml;
    String xml;
    String pathUrl;
    String mapClass;

    ShapeStructure shapeInfo;
    SpacePositionStructure spacePosition;
    ConceptStructure concept;
    List<PropertyStructure> properties;
    List<ProcessStructure> processes;
    List<ElementRelationStructure> elementRelations;

    Integer width;
    Integer height;
}
