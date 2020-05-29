package com.sebastian.quarkusoauth;

import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

/**
 * demo para tener un provider oauth2 usando password para la autorización y jwt
 * para el token.
 *
 * @author Sebastian Ávila Á.
 */
@Path("/oauth")
public class RecursosOauth {

  @Path("token")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response generar(@BeanParam Oauth2Request request) {
    if ((request.getGrantType().equals("client_credentials")
            || request.getGrantType().equals("password"))
            && (request.getUsername().equals("savila") || request.getClientId().equals("savila"))) {
      return Response.ok(Oauth2Utils.generar(request)).build();
    } else {
      return Response.status(403).build();
    }
  }

  @Path("introspect")
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public Response introspect(@FormParam("token") String token, final @Context HttpHeaders hdr) {
    hdr.getRequestHeaders().forEach((a, b) -> {
      System.out.println(a + ": " + b);
    });
    System.out.println(token);
    final DecodedJWT jwt = JWT.decode(token);
    System.out.println(jwt);
    /*
    return Response.ok("{\n"
            + "  \"client_id\"                  : \"client_id\",\n"
            + "  \"active\"  : true,\n"
            + "  \"scope\"                      : [\"scope-1\", \"scope-2\", \"P:scope-1:x\"],\n"
            + "  \"roles\"                      : [\"app-a:a\", \"app-a:b\"],\n"
            + "  \"authorities\"                      : [\"root\",\"ROLE_admin\", \"scope 3\"]\n"
            + "}").build();*/
    
    return Response.ok("{\n" +
"  \"sub\": \"86dc8999-45f6-46e1-ae4e-d0ff85b78d5f\",\n" +
"  \"email_verified\": false,\n" +
"  \"iss\": \"https://server\",\n" +
"  \"custom:id\": \"4035\",\n" +
            "  \"active\": true,\n" +
"  \"phone_number_verified\": false,\n" +
"  \"clt\": [\n" +
"    1808,\n" +
"    161\n" +
"  ],\n" +
"  \"cognito:username\": \"savila\",\n" +
"  \"aud\": \"2p68s4ofsh7316ohiguem0ifir\",\n" +
"  \"hbl\": \"true\",\n" +
"  \"event_id\": \"f8c2aad2-62b8-4fe0-9def-85887460c79a\",\n" +
"  \"token_use\": \"id\",\n" +
"  \"scope\": [\n" +
"    \"scope-1\",\n" +
"    \"1:P:2\",\n" +
"    \"1:F:1\",\n" +
"    \"1:P:7\",\n" +
"    \"1:P:8\",\n" +
"    \"1:P:5\",\n" +
"    \"1:P:6\",\n" +
"    \"1:P:3\",\n" +
"    \"1:P:4\"\n" +
"  ],\n" +
"  \"auth_time\": 1579003813,\n" +
"  \"name\": \"savila\",\n" +
"  \"exp\": 1579007413,\n" +
"  \"iat\": 1579003813,\n" +
"  \"family_name\": \"savila\",\n" +
"  \"email\": \"sss@sss.cl\"\n" +
"}").build();
    
  }
}
