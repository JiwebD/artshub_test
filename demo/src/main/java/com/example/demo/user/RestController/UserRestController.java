package com.example.demo.user.RestController;

import com.example.demo.user.Dto.UserDto;
import com.example.demo.user.Entity.User;
import com.example.demo.user.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role("ROLE_USER")
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("회원가입 성공");
    }
}
