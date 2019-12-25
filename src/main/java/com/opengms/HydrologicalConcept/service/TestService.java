package com.opengms.HydrologicalConcept.service;

import com.opengms.HydrologicalConcept.dao.Impl.TestDao;
import com.opengms.HydrologicalConcept.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 17:30
 */

@Service
public class TestService {

    @Autowired
    TestDao testDao;

    public String insertUser(User user)
    {
        return testDao.insert(user);
    }
}
