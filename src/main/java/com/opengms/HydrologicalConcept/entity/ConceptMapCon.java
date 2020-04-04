package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

@Data
public class ConceptMapCon {
    String id;
    String geoId;
    String name;
    String description;
    String graphXml;
    String xml;
    String pathUrl;
    String mapClass;
    String[] tags;
}
