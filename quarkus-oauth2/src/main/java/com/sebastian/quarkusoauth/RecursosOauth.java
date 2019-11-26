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
  public Response introspect(@FormParam("token") String token) {
    System.out.println(token);
    final DecodedJWT jwt = JWT.decode(token);
    System.out.println(jwt);
    return Response.ok("{\n"
            + "  \"client_id\"                  : \"client_id\",\n"
            + "  \"active\"  : true,\n"
            + "  \"scope\"                      : [\"scope-1\", \"scope-2\"],\n"
            + "  \"authorities\"                      : [\"root\",\"ROLE_admin\"]\n"
            + "}").build();
  }
}
