package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.dto.GeoIconDTO;
import com.opengms.HydrologicalConcept.entity.GeoIcon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GeoIconDao extends MongoRepository<GeoIcon,String> {
    GeoIcon findGeoIconByGeoId(String geoId);

    List<GeoIconDTO> findGeoIconByNameContains(String key);
}
