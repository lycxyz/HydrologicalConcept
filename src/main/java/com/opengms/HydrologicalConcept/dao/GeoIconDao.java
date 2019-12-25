package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.GeoIcon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeoIconDao extends MongoRepository<GeoIcon,String> {
    GeoIcon findGeoIconByGeoId(String geoId);
}
