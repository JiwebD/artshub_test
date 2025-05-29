//package com.example.demo.user.repository;
//
//import com.example.demo.user.entity.JwtToken;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface JwtTokenRepository extends JpaRepository<JwtToken,Long> {
//    //AccessToken 을 받아 Entity 반환
//    JwtToken findByAccessToken(String accessToken);
//
//    //Username 을 받아 Entity 반환
//    JwtToken findByUsername(String username);
//
//    void deleteByAccessToken(String token);
//}
