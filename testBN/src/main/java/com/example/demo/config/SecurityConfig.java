package com.example.demo.config;


import com.example.demo.user.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity            //Spring Security 설정 클래스임을 명시
@RequiredArgsConstructor
public class SecurityConfig {

	private final PrincipalDetailsService principalDetailsService;

	//SecurityFilterChain	시큐리티 설정의 핵심 (권한, 로그인 경로 등)
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/login", "/join", "/error").permitAll()
						.anyRequest().authenticated()
				)
				//.formLogin()	폼 로그인 방식 설정 (loginPage, loginProcessingUrl 등)
				.formLogin(form -> form
						.loginPage("/login")			//커스텀 로그인 페이지
						.loginProcessingUrl("/login")	//로그인 요청 처리 URL (POST)
						.defaultSuccessUrl("/")			//로그인 성공 시 이동할 URL
						.permitAll()
				);

		return http.build();

	}

	// AuthenticationManager 설정 (필수)
	// AuthenticationManager	우리가 만든 PrincipalDetailsService 를 내부적으로 사용함
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	//POST /login 요청을 보내면 →
	//PrincipalDetailsService.loadUserByUsername() 가 호출되어 로그가 찍힐 것.

}


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//	@Autowired
//	private CustomLoginSuccessHandler customLoginSuccessHandler;
//	@Autowired
//	private CustomLogoutHandler customLogoutHandler;
//	@Autowired
//	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
//	@Autowired
//	private UserRepository userRepository;
//	@Autowired
//	private JwtTokenProvider jwtTokenProvider;
//	@Autowired
//	private JwtTokenRepository jwtTokenRepository;
//	@Autowired
//	private RedisUtil redisUtil;
//
//
//	@Bean
//	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//		//CSRF비활성화
//		http.csrf((config)->{config.disable();});
//		//CSRF토큰 쿠키형태로 전달
////		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		//권한체크
//		http.authorizeHttpRequests((auth)->{
//			auth.requestMatchers("/","/join","/login","/validate").permitAll();
//			auth.requestMatchers("/user").hasRole("USER");
//			auth.requestMatchers("/manager").hasRole("MANAGER");
//			auth.requestMatchers("/admin").hasRole("ADMIN");
//			auth.anyRequest().authenticated();
//		});
//		//-----------------------------------------------------
//		// [수정] 로그인(직접처리 - UserRestController)
//		//-----------------------------------------------------
//		http.formLogin((login)->{
//			login.disable();
////            login.permitAll();
////            login.loginPage("/login");
////            login.successHandler(customLoginSuccessHandler());
////            login.failureHandler(new CustomAuthenticationFailureHandler());
//		});
//
//		//로그아웃
//		http.logout((logout)->{
//			logout.permitAll();
//			logout.addLogoutHandler(customLogoutHandler);
//			logout.logoutSuccessHandler(customLogoutSuccessHandler);
//		});
//		//예외처리
//
//		http.exceptionHandling((ex)->{
//			ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//			ex.accessDeniedHandler(new CustomAccessDeniedHandler());
//		});
//
//		//OAUTH2-CLIENT
//		http.oauth2Login((oauth2)->{
//			oauth2.loginPage("/login");
//		});
//
//		//SESSION INVALIDATED
//		http.sessionManagement((sessionManagerConfigure)->{
//			sessionManagerConfigure.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		});
//
//		//JWT FILTER ADD
//		http.addFilterBefore(new JwtAuthorizationFilter(userRepository,jwtTokenProvider,jwtTokenRepository,redisUtil), LogoutFilter.class);
//		//-----------------------------------------------
//		//[추가] CORS
//		//-----------------------------------------------
//		http.cors((config)->{
//			config.configurationSource(corsConfigurationSource());
//		});
//
//		return http.build();
//
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	//-----------------------------------------------------
//	//[추가] CORS
//	//-----------------------------------------------------
//	@Bean
//	CorsConfigurationSource corsConfigurationSource(){
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowedHeaders(Collections.singletonList("*")); //허용헤더
//		config.setAllowedMethods(Collections.singletonList("*")); //허용메서드
//		config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000"));  //허용도메인
//		config.setAllowCredentials(true); // COOKIE TOKEN OPTION
//		return new CorsConfigurationSource(){
//			@Override
//			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//				return config;
//			}
//		};
//	}
//	//-----------------------------------------------------
//	//[추가] ATHENTICATION MANAGER 설정 - 로그인 직접처리를 위한 BEAN
//	//-----------------------------------------------------
//	@Bean
//	public AuthenticationManager authenticationManager(
//			AuthenticationConfiguration authenticationConfiguration) throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//
//}
