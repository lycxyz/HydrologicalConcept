package com.opengms.HydrologicalConcept.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("地理图标对象：GeoIcon")
@Data
public class GeoIcon {
    String geoId;
    String name;
    String description;
    String pathUrl;
//    String iconClass;
    List<String> tags;

//    List<ConceptMap> relatedConceptMaps;
//    List<GeoIcon> relatedGeoIcons;
}
