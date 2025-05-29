package com.example.demo.user.dto;

import com.example.demo.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// getter, setter 자동 생성
@NoArgsConstructor	// 기본 생성자
@AllArgsConstructor	// 모든 필드 생성자
@Builder	// 빌더 패턴 지원
public class UserDto {
//	private String username;
//	private String password;
//	private String role;
//
//	//OAUTH2 CLIENT INFO
//	private String provider;
//	private String providerId;
//	//DTO->ENTITY
//	public User toEntity(){
//		return User.builder()
//				.username(this.username)
//				.password(this.password)
//				.role("ROLE_USER")
//				.build();
//	}
//	//ENTITY->DTO
//	public static UserDto toDto(User user){
//		return UserDto.builder()
//					.username(user.getUsername())
//					.password(user.getPassword())
//					.role(user.getRole())
//					.build();
//	}
	
	private String username;
	private String password;
	private String role;

	//Entity -> DTO 변환
	//Spring Security는 UserDetails를 구현한 객체(우리가 만든 PrincipalDetails)로
	//로그인 처리를 한다.
	//그런데 DB에서 꺼낸 건 User 엔티티.
	//그러면 UserDto 로 한번 감싸서 Spring Security에 넘기기 쉽게 만들어 줄 필요가 있음.
	//흐름 DB -> User (Entity) -> UserDto(Entity-> Dto) -> PrincipalDetails(Spring Security에서 사용)
	public static UserDto toDto(User user) {
		return UserDto.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.role(user.getRole())
				.build();

		//다음 단계 UserDto를 품고 있는 로그인 인증 객체 PrincipalDetails.java 작성.
	}
}
