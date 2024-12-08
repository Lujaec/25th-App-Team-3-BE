package org.yapp.oauth.application;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.yapp.oauth.domain.AuthenticatedUser;

public interface CustomOAuth2UserService {

    boolean supports(OAuth2UserRequest userRequest);

    AuthenticatedUser createOrLoadUser(OAuth2User authenticatedUser);
}