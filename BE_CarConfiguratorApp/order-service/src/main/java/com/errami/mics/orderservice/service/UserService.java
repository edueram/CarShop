package com.errami.mics.orderservice.service;

import com.errami.mics.orderservice.dto.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserInfo extractUserInfo() {

        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();

        String userId = token.getToken().getSubject();
        String userName = token.getToken().getClaimAsString("preferred_username");
        String name = token.getToken().getClaimAsString("name");
        //String givenName = token.getToken().getClaimAsString("given_name");
        //String familyName = token.getToken().getClaimAsString("family_name");
        String email = token.getToken().getClaimAsString("email");

        return new UserInfo(userId, userName, name, email );
    }
}

