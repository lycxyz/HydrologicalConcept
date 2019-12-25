package com.opengms.HydrologicalConcept.dao;

import com.opengms.HydrologicalConcept.dto.LoginDTO;
import com.opengms.HydrologicalConcept.entity.User;
import com.opengms.HydrologicalConcept.vo.UserVo;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/19 19:41
 */
public interface IUserDao {
    void addUser(User user);
    User findUserByEmail(String email);
    UserVo login(LoginDTO loginDTO);
}
