package com.sebastian.quarkusoauth;

import javax.ws.rs.FormParam;

/**
 * contiene los parametros requeridos para generar/refrescar el token.
 *
 * @author Sebastian Ávila Á.
 */
public class Oauth2Request {
  @FormParam("grant_type")
  private String grantType;
  @FormParam("client_id")
  private String clientId;
  @FormParam("client_secret")
  private String clientSecret;
  @FormParam("username")
  private String username;
  @FormParam("password")
  private String password;
  /**
   * @return the grantType
   */
  public String getGrantType() {
    return grantType;
  }
  /**
   * @param grantType the grantType to set
   */
  public void setGrantType(String grantType) {
    this.grantType = grantType;
  }
  /**
   * @return the clientId
   */
  public String getClientId() {
    return clientId;
  }
  /**
   * @param clientId the clientId to set
   */
  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }
  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }
  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * @return the clientSecret
   */
  public String getClientSecret() {
    return clientSecret;
  }
  /**
   * @param clientSecret the clientSecret to set
   */
  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }
  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
