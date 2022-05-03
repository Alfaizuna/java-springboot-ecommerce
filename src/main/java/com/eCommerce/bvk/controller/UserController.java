package com.eCommerce.bvk.controller;

import com.eCommerce.bvk.common.BaseResponse;
import com.eCommerce.bvk.common.CommonCode;
import com.eCommerce.bvk.common.CommonMessage;
import com.eCommerce.bvk.entity.User;
import com.eCommerce.bvk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //create data user
    @PostMapping(value = "/add")
    public BaseResponse addNewUser(@RequestBody User param){
        userRepository.save(param);
        return new BaseResponse(CommonMessage.SAVED, CommonCode.SUCCESS);
    }
}
