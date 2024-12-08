package org.yapp.user.domain;

import org.yapp.user.domain.enums.LoginType;
import org.yapp.user.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String email;
    private String nickName;
    @Enumerated(value = EnumType.STRING)
    private LoginType loginType;
    @Column(name = "oauth2_client_id")
    private String oauth2ClientId;
}
