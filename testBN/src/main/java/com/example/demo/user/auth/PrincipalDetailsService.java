package com.example.demo.user.auth;

import com.example.demo.user.dto.UserDto;
import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	//	UserDetailsService 스프링 시큐리티가 로그인할 때 사용하는 인터페이스
	//	loadUserByUsername() 로그인 시 아이디로 사용자 정보를 가져오는 메서드

	// UserRepository	DB에서 사용자 조회
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("[PrincipalDetailsService] 로그인 요청 아이디 : {}", username); // : {} 포맷팅방식

		User user = userRepository.findByUsername(username);

		if(user == null){
			log.warn("[PrincipalDetailsService] 사용자 없음 : {}", username);
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}

		log.info("[PrincipalDetailsService] 사용자 조회 성공 : {}" , user.getUsername());

		//실제로 로그인을 시도할 경우 아래 로그가 출력됨.
		//[PrincipalDetailsService] 로그인 요청 아이디: testuser
		//[PrincipalDetailsService] 사용자 조회 성공: testuser

		// PrincipalDetails 우리가 만든 인증 사용자 정보 클래스 (User → Spring이 이해할 수 있도록 변환)
		// User -> UserDto -> PrincipalDetails로 감싸서 반환
		return new PrincipalDetails(UserDto.toDto(user));
	}
}
//@Service
//@Slf4j
//public class PrincipalDetailsService implements UserDetailsService{
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		System.out.println("loadUserByUsername .. " + username);
//		Optional<User> userOption  = userRepository.findById(username);
//		if(userOption.isEmpty())
//			throw new UsernameNotFoundException(username + " 존재하지 않는 계정입니다.");
//
//		//entity-> dto
//		UserDto userDto = UserDto.toDto( userOption.get()    );
//		return new PrincipalDetails(userDto);
//	}
//
//}


