package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

@Data
public class SubGeoRule {
    String[] aliasOf;
    String[] kindOf;
    String[] partOf;
    String[] attributeOf;
    String[] instanceOf;
    String[] parentOf;
    String[] componentsOf;
    String[] orderOf;
    String[] positionOfElements;
}
