package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

import java.util.List;

@Data
public class SpacePositionStructure {
    String desc;
    String[] tags;
    List<UserImage> relateImages;
}
