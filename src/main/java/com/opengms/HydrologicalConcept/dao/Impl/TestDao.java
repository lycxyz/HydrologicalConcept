package com.opengms.HydrologicalConcept.dao.Impl;

import com.opengms.HydrologicalConcept.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 17:06
 */

@Component
public class TestDao {

    @Value("${spring.data.mongodb.userCollection}")
    private String userCollection;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String insert(User user)
    {
        return null;
//        mongoTemplate.insert(user,userCollection);
//        return user.getId();
    }
}
