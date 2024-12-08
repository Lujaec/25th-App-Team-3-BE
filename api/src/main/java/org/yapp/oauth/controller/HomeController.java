package org.yapp.oauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.yapp.oauth.domain.AuthenticatedUser;

// JWT 서비스 코드 추가
@Controller
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<String> home(@AuthenticationPrincipal AuthenticatedUser user) {
        System.out.println("user = " + user);

        var email = user.getEmail();
        var nickName = user.getNickName();

        return ResponseEntity.ok("Email: " + email + ", NickName: " + nickName);
    }
}