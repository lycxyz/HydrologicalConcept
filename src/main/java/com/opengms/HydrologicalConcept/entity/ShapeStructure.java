package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class ShapeStructure {
    String desc;
    String[] tags;
    List<UserImage> relateImages;
}
