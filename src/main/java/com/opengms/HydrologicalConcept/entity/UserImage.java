package com.opengms.HydrologicalConcept.entity;

import lombok.Data;

@Data
public class UserImage {
    String geoId;
    String name;
    String description;
    String pathUrl;
    String[] tags;
}
