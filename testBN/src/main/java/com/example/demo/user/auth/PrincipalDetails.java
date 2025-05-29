package com.example.demo.user.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

//User(Entity) -> UserDto  -> PrincipalDetails(Spring Security가 사용하는 인증 사용자정보
@RequiredArgsConstructor //
public class PrincipalDetails implements UserDetails {

	private final UserDto userDto;


	//사용자 권한(ROLE_USER, ROLE_ADMIN 등)을 Spring Security가 이해하는 형태로 반환
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한(Role)을 Spring Security가 인식할 수 있게 변환
		return List.of(new SimpleGrantedAuthority(userDto.getRole()));
	}

	//	로그인 시 비밀번호 비교용
	@Override
	public String getPassword() {
		return userDto.getPassword();
	}

	//로그인 ID (username) 반환
	@Override
	public String getUsername() {
		return userDto.getUsername();
	}

	// 아래 4개는 보통 true 고정
	// 계정 잠김 여부 등 – 우리는 기본값 true 사용
	// 나중에 추가 하고 싶으면 User 엔티티에 코드 추가
	//private boolean locked;
	//private boolean enabled;
	//private LocalDate passwordUpdatedAt;

	// PrincipalDetails에서 코드 변경
	//@Override
	//public boolean isAccountNonLocked() {
	//    return !userDto.isLocked();
	//}
	//
	//@Override
	//public boolean isEnabled() {
	//    return userDto.isEnabled();
	//}
	//
	//@Override
	//public boolean isCredentialsNonExpired() {
	//    return ChronoUnit.DAYS.between(userDto.getPasswordUpdatedAt(), LocalDate.now()) < 90;
	//}


	//계정이 만료되지 않았는가
	@Override
	public boolean isAccountNonExpired() { return true; }
	//계정이 잠겨있지 않은가
	@Override
	public boolean isAccountNonLocked() { return true; }
	//비밀번호가 만료되지 않았는가
	@Override
	public boolean isCredentialsNonExpired() { return true; }
	//계정이 활성화 상태인가
	@Override
	public boolean isEnabled() { return true; }

	//우리가 직접 꺼내 쓸 수 있도록 DTO 반환용 메서드
	public UserDto getUserDto() {
		return this.userDto;
	}
}




//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class PrincipalDetails implements UserDetails,OAuth2User {
//	private UserDto userDto;
//	public PrincipalDetails(UserDto userDto){
//		this.userDto = userDto;
//	}
//	//----------------------------
//	// OAuth2User
//	//----------------------------
//	Map<String, Object> attributes;
//	String access_token;
//	@Override
//	public Map<String, Object> getAttributes() {
//		return attributes;
//	}
//	@Override
//	public String getName() {
//		return userDto.getUsername();
//	}
//	//----------------------------
//
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection <GrantedAuthority> authorities = new ArrayList();
//		authorities.add(new SimpleGrantedAuthority(userDto.getRole()));
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return userDto.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return userDto.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//}
