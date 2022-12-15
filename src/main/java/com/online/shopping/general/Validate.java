package com.online.shopping.general;

import com.online.shopping.jwtconfig.JwtTokenUtil;
import com.online.shopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validate {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    public int getUserId(String authorization) {
        String jwtToken = authorization.substring(7);
        return userRepository.findIdByUserName(jwtTokenUtil.getUsernameFromToken(jwtToken));
    }

}
