package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class GeoIcon {
    String geoId;
    String name;
    String description;
    String pathUrl;
    String iconClass;
    List<String> tags;

    List<ConceptMap> relatedConceptMaps;
    List<GeoIcon> relatedGeoIcons;
}
