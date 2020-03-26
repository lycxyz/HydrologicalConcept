package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

@Data
public class GeoRule {
    String name;
    SubGeoRule concept;
    SubGeoRule spacePosition;
    SubGeoRule shape;
    SubGeoRule process;
    SubGeoRule elementRelation;
    SubGeoRule property;
}
