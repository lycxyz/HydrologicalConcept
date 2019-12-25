package com.opengms.HydrologicalConcept.controller;
import com.opengms.HydrologicalConcept.bean.ResponseCode;
import com.opengms.HydrologicalConcept.bean.ResponseResult;
import com.opengms.HydrologicalConcept.dto.LoginDTO;
import com.opengms.HydrologicalConcept.entity.User;
import com.opengms.HydrologicalConcept.exception.RequestException;
import com.opengms.HydrologicalConcept.service.UserService;
import com.opengms.HydrologicalConcept.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Description: HydrologicalConcept
 * <p>111
 * Created by LYC on 2019/12/19 19:37
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    UserService userService;

    @CrossOrigin(value = "*")
    @PostMapping(value = "/signup")
    public ResponseResult signup(@RequestBody User user){
        try{
            userService.signup(user);
        }
        catch(RequestException exception){
            return ResponseResult.result(exception.getCode());
        }
        return ResponseResult.result(ResponseCode.OK);
    }

    @CrossOrigin(value = "*")
    @RequestMapping(value = "/login")
    public ResponseResult<UserVo> login(@RequestBody LoginDTO loginDTO){
        UserVo userVo;
        try {
            userVo = userService.login(loginDTO);

        }catch(RequestException exception){
            return ResponseResult.result(exception.getCode());

        }
        return ResponseResult.result(ResponseCode.OK,userVo);
    }

}
