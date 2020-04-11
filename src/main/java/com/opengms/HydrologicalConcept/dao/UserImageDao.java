package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.UserImage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserImageDao extends MongoRepository<UserImage,String> {
    UserImage findByGeoId(String geoId);
    List<UserImage> findByConceptId(String conceptId);
}
