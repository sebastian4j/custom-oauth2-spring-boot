package com.sebastian.qkoauth2;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/")
@ApplicationScoped
public class TokenSecuredResource {

  @GET
  @Path("/no-seguro")
  @Produces(MediaType.TEXT_PLAIN)
  @PermitAll
  public String noSeguro(@Context SecurityContext ctx) {
    final var caller = ctx.getUserPrincipal();
    return (caller != null ? caller.getName() : "anonimo") + " " + ctx.getAuthenticationScheme();
  }
  
  @GET
  @Path("/roles")
  @RolesAllowed({"1:P:1"})
  @Produces(MediaType.TEXT_PLAIN)
  public String seguro(@Context SecurityContext ctx) {
    final var caller = ctx.getUserPrincipal();
    return (caller != null ? caller.getName() : "anonimo") + " " + ctx.getAuthenticationScheme();
  }
}
