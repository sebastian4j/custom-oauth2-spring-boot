package com.sebastian.sboauth;

import com.auth0.jwt.JWT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * 
 * @author Sebastián Ávila A.
 */
@EnableResourceServer
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class App extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/publico/**").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    // necesario si viene un aud distinto a oauth2-resource
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("2p68s4ofsh7316ohiguem0ifir");
    }

  public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RestController
    public class RecursosProtegidos {

        @GetMapping("/privado/scope")
        @PreAuthorize("#oauth2.hasScope('1:P:2')")
        public String scope(Principal principal) {
            return "scope correcto " + principal.getName();
        }

        @GetMapping("/privado/noscope")
        @PreAuthorize("#oauth2.hasScope('scope-4')")
        public String noscope(Principal principal) {
            return "scope  correcto: " + principal.getName();
        }

        @GetMapping("/privado/auth")
        @PreAuthorize("hasAuthority('root')")
        public String authority(Principal principal) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            OAuth2AuthenticationDetails dt = (OAuth2AuthenticationDetails) auth.getDetails();
            return "authority correcto: " + principal.getName() + " iss: " + JWT.decode(dt.getTokenValue()).getClaim("iss").asString();
        }

        @GetMapping("/privado/noauth")
        @PreAuthorize("hasAuthority('ruto')")
        public String noauthority(Principal principal) {
            return "authority correcto: " + principal.getName();
        }

        @GetMapping("/privado/role")
        @PreAuthorize("hasRole('admin')")
        public String role(Principal principal) {
            return "role correcto: " + principal.getName();
        }

        @GetMapping("/privado/hola")
        public String sinRestriccion() {
            return "chao";
        }

        @GetMapping("/publico/chao")
        public String publico() {
            return "hola";
        }

    }
}
