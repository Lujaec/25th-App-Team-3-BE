package org.yapp.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yapp.user.domain.UserEntity;
import org.yapp.user.domain.enums.LoginType;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserByOauth2ClientIdAndLoginType(String oauth2Id, LoginType loginType);
}
