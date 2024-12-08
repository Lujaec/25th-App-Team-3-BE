package org.yapp.oauth.domain;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.yapp.user.domain.UserEntity;
import org.yapp.user.domain.enums.LoginType;
import org.yapp.user.domain.enums.Role;

import java.util.Map;

public class GoogleUser {

    private final OAuth2User oAuth2User;

    public GoogleUser(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .role(Role.USER)
                .email(email())
                .nickName(nickName())
                .loginType(LoginType.GOOGLE)
                .oauth2ClientId(oAuth2User.getName())
                .build();
    }

    private Map<String, Object> attributes() {
        return oAuth2User.getAttributes();
    }

    private String nickName() {
        return String.valueOf(attributes().get("name"));
    }

    private String email() {
        return String.valueOf(attributes().get("email"));
    }
}