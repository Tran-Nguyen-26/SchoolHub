package com.schoolmanager.schoolhub.security.jwt;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.schoolmanager.schoolhub.security.user.SchoolUserDetails;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

  @Value("${auth.token.jwtSecret}")
  private String jwtSecret;

  @Value("${auth.token.expirationInMils}")
  private int expirationTime;

  public String generateTokenForUser(Authentication authentication) {
    SchoolUserDetails userPrincipal = (SchoolUserDetails) authentication.getPrincipal();
    List<String> permissions = userPrincipal.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .toList();
    return Jwts.builder()
        .subject(userPrincipal.getEmail())
        .claim("id", userPrincipal.getId())
        .claim("permission", permissions)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expirationTime))
        .compact();
  }

  private SecretKey key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  public String getEmailFromToken(String token) {
    return Jwts.parser()
        .verifyWith(key())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public boolean validatedToken(String token) {
    try {
      Jwts.parser().verifyWith(key()).build().parseSignedClaims(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

}
