package com.opengms.HydrologicalConcept.dao.Impl;

import com.opengms.HydrologicalConcept.dao.IUserDao;
import com.opengms.HydrologicalConcept.dto.LoginDTO;
import com.opengms.HydrologicalConcept.entity.User;
import com.opengms.HydrologicalConcept.exception.RequestException;
import com.opengms.HydrologicalConcept.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.opengms.HydrologicalConcept.bean.ResponseCode;

//import com.opengms.HydrologicalConcept.exception.RequestException;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 19:41
 */
@Component
public class UserDao implements IUserDao {
    @Autowired
    MongoTemplate mongoTemplate;
    @Value("${spring.data.mongodb.userCollection}")
    private String userCollection;

    @Override
    public void addUser(User user) {
        User findUser = this.findUserByEmail(user.getEmail());
        if (findUser != null)
        {
            throw RequestException.fail(ResponseCode.SIGN_UP_FAIL_EMAIL_EXIST);
        }
        try
        {
            mongoTemplate.insert(user,userCollection);
        }catch(Exception e)
        {
            throw RequestException.fail(ResponseCode.SIGN_UP_FAIL);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query,User.class,userCollection);
    }

    @Override
    public UserVo login(LoginDTO loginDTO) {
        if( "".equals(loginDTO.getEmail()) || "".equals(loginDTO.getPassword()) ){
            throw RequestException.fail(ResponseCode.SING_IN_INPUT_EMPTY);
        }
        User user = findUserByEmail(loginDTO.getEmail());
        if (user == null || !loginDTO.getPassword().equals(user.getPassword()))
        {
            throw RequestException.fail(ResponseCode.SIGN_IN_INPUT_FAIL);
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }
//    @Value("${spring.data.mongodb.userCollection}")

}
