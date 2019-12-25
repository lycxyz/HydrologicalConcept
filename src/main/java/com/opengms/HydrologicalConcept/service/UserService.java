package com.opengms.HydrologicalConcept.service;

import com.opengms.HydrologicalConcept.dao.IUserDao;
import com.opengms.HydrologicalConcept.dto.LoginDTO;
import com.opengms.HydrologicalConcept.entity.User;
import com.opengms.HydrologicalConcept.exception.RequestException;
import com.opengms.HydrologicalConcept.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 19:39
 */
@Service
public class UserService {
    @Resource(name="userDao")
    IUserDao userDao;


    public UserVo login(LoginDTO loginDTO) throws RequestException {
        return userDao.login(loginDTO);
    }


    public void signup(User user) {
        userDao.addUser(user);
    }
}
