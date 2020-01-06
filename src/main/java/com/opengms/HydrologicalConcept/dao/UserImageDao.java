package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.entity.UserImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserImageDao extends MongoRepository<UserImage,String> {
    UserImage findByGeoId(String geoId);
}
