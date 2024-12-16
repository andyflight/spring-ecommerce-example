package com.fice.ecommerce.presenter.security.encoder;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {

  @Override
  public String encode(String rawPassword) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error encoding password", e);
    }
  }

  @Override
  public boolean matches(String rawPassword, String encodedPassword) {
    return encode(rawPassword).equals(encodedPassword);
  }
}
