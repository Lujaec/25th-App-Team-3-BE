package org.yapp.oauth.application;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yapp.oauth.domain.AuthenticatedUser;
import org.yapp.oauth.domain.KakaoUser;
import org.yapp.user.dao.UserRepository;
import org.yapp.user.domain.UserEntity;
import org.yapp.user.domain.enums.LoginType;

@Service
public class KakaoOAuth2UserService implements CustomOAuth2UserService {

    private static final String REGISTRATION_ID = "kakao";
    private final UserRepository repository;

    public KakaoOAuth2UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(OAuth2UserRequest userRequest) {
        return REGISTRATION_ID.equals(userRequest.getClientRegistration().getRegistrationId());
    }

    @Transactional
    @Override
    public AuthenticatedUser createOrLoadUser(OAuth2User authenticatedUser) {
        var subject = authenticatedUser.getName();
        var optional = repository.findUserByOauth2ClientIdAndLoginType(subject, LoginType.KAKAO);
        UserEntity user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            user = new KakaoUser(authenticatedUser).toUserEntity();
            repository.save(user);
        }
        return AuthenticatedUser.of(user, authenticatedUser);
    }
}