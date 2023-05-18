package com.kul.logistics.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kul.logistics.service.JwtService;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author Shane Paulus
 * <p>
 * Date Created: 14-Sep-2022
 */

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {

  private final UserDetailsService userDetailsService;

  @Override
  public String generateToken(String email) {
    return JWT.create()
        .withSubject(email)
        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
        .withIssuer("kul-logistics")
        .sign(Algorithm.HMAC256("secret"));
  }

  @Override
  public boolean isTokenExpired(String token, Date currentDate) {
    return JWT.decode(token)
        .getExpiresAt()
        .getTime() < currentDate.getTime();
  }
}
