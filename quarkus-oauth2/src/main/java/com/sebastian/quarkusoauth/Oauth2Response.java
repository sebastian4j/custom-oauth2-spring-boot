package com.sebastian.quarkusoauth;

import javax.json.bind.annotation.JsonbProperty;

/**
 * response para el cliente que solicita la autenticación.
 * 
 * @author Sebastian Ávila Á.
 */
public class Oauth2Response {
  @JsonbProperty("access_token")
  private String accessToken;  
  @JsonbProperty("expires_in")
  private long expiresIn;
  @JsonbProperty("token_type")
  private String tokenType;
  /**
   * @return the accessToken
   */
  public String getAccessToken() {
    return accessToken;
  }
  /**
   * @param accessToken the accessToken to set
   */
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  /**
   * @return the expiresIn
   */
  public long getExpiresIn() {
    return expiresIn;
  }
  /**
   * @param expiresIn the expiresIn to set
   */
  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }
  /**
   * @return the tokenType
   */
  public String getTokenType() {
    return tokenType;
  }
  /**
   * @param tokenType the tokenType to set
   */
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }
  
}
