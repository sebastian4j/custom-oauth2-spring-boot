package com.sebastian.quarkusoauth;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * metodos utiles para oauth2.
 *
 * @author Sebastian Ávila Á.
 */
public class Oauth2Utils {

  static {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }
  private static final Algorithm ALGORITMO = Algorithm.HMAC256("secret");

  /*
  public static void main(final String[] args) throws InterruptedException {
    final String jwt = generar();
    System.out.println("Generado: " + jwt);
    mostrar(jwt);
    extenderTiempo(jwt);
  }
*/

  private static Date ahora() {
    return new Date(
            ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")).toInstant().toEpochMilli());
  }

  public static Oauth2Response generar(Oauth2Request req) {
    final var response = new Oauth2Response();
    response.setExpiresIn(300);
    response.setTokenType("bearer");
    response.setAccessToken(generar(Date.from(Instant.now().plus(10, ChronoUnit.HOURS)), req));
    return response;
  }

  public static String generar(Date expira, Oauth2Request req) {
    return JWT.create().withIssuer("Sebastián Ávila S.A.").withIssuedAt(ahora()).withSubject(req.getUsername())
             
            .withArrayClaim("prm", new Integer[]{1, 2, 3}).withClaim("nm", "Sebastian Avila A.")
            .withJWTId(UUID.randomUUID().toString()).withArrayClaim("prf", new Integer[]{11, 22, 33})
            .withArrayClaim("roles", new String[]{"a", "b", "c"})
            .withArrayClaim("groups", new String[]{"a", "b", "c"})            
            .withClaim("typ", "Bearer")
            .withArrayClaim("scope", new String[] {"custom_mod"})
            .withArrayClaim("clt", new Integer[]{34, 9, 90}).withExpiresAt(expira).sign(ALGORITMO);
    // nombres cortos: keep JWTs as small as possible
  }

  /*
  public static void extenderTiempo(final String token) {
    System.out.println("::extender tiempo::");
    if (esValido(token)) {
      final JWTVerifier verifier = JWT.require(ALGORITMO).build();
      final DecodedJWT jwt = verifier.verify(token);
      final Date expira = jwt.getExpiresAt();
      System.out.println("date expira: " + expira);
      DecodedJWT dec = JWT.decode(token);
      System.out.println("solicitado a: " + dec.getIssuedAt());
      final ZonedDateTime datezdt
              = ZonedDateTime.ofInstant(Instant.ofEpochMilli(expira.getTime()), ZoneId.of("UTC"));
      System.out.println("zone expira: " + datezdt);
      final ZonedDateTime plus = datezdt.plusMinutes(15);

      final String nuevo = generar(new Date(plus.toInstant().toEpochMilli()));
      if (esValido(nuevo)) {
        final DecodedJWT extendido = JWT.decode(nuevo);
        System.out.println(extendido.getExpiresAt());
        mostrar(nuevo);
      } else {
        System.out.println("no se puede extender");
      }
    }
  }
*/

  public static boolean esValido(final String token) {
    boolean esValido = false;
    try {
      JWT.require(ALGORITMO).build().verify(token);
      esValido = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return esValido;
  }

  public static void mostrar(final String token) {
    if (esValido(token)) {
      final DecodedJWT jwt = JWT.decode(token);      
      
      System.out.println("algorithm: " + jwt.getAlgorithm());
      System.out.println("type: " + jwt.getType());
      System.out.println("issuer: " + jwt.getIssuer());
      System.out.println("payload: " + jwt.getPayload());
      System.out.println("id: " + jwt.getId());
      System.out.println("sub: " + jwt.getSubject());
      System.out.println("permisos: ");
      Arrays.asList(jwt.getClaim("prm").asArray(Integer.class)).forEach(System.out::println);
      System.out.println("perfiles:");
      Arrays.asList(jwt.getClaim("prf").asArray(Integer.class)).forEach(System.out::println);
      System.out.println("nombre: " + jwt.getClaim("nm").asString());
      System.out.println("clientes:");
      Arrays.asList(jwt.getClaim("clt").asArray(Integer.class)).forEach(System.out::println);
    } else {
      System.out.println("token expirado");
    }
  }
}
