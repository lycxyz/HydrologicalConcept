package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class PropertyStructure {
    String type;
    String description;
    String[] tags;
    List<UserImage> relateImages;
}
