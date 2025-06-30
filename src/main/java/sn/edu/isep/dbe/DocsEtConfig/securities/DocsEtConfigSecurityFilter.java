package sn.edu.isep.dbe.DocsEtConfig.securities;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.edu.isep.dbe.DocsEtConfig.entities.Droit;
import sn.edu.isep.dbe.DocsEtConfig.entities.Role;
import sn.edu.isep.dbe.DocsEtConfig.entities.User;
import sn.edu.isep.dbe.DocsEtConfig.repositories.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DocsEtConfigSecurityFilter extends OncePerRequestFilter {
private final Logger logger = LoggerFactory.getLogger("MySecurityFilter");
    private final UserRepository userRepository;

    public DocsEtConfigSecurityFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws SecurityException, IOException, ServletException {
    logger.debug("execution methode diFilterInternal de la classe DoacsEtConfigSecurityFilter");
    logger.info("requete sur url" +request.getRequestURI());
    String userAgent = request.getHeader("User-Agent");
    if (userAgent.contains("win")){
        logger.warn("utilisation de windows:"+userAgent);
    }
    String login = request.getHeader("email");
    String password = request.getHeader("password");
        Optional<User> userData = userRepository.findByEmail(login);
        if (userData.isPresent()){
            List<GrantedAuthority> authorities = new ArrayList<>();
            User user = userData.get();
            if (user.getPassword().equals(password)){
            for (Role role : user.getRoles()){
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
            for (Droit droit : user.getDroits()){
                authorities.add(new SimpleGrantedAuthority(droit.getName()));
            }
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    user, user.getEmail(),authorities
            ));
            }
        }
    logger.error("requete sur url"+request.getRequestURI());
        List<GrantedAuthority> roles = List.of(
                new SimpleGrantedAuthority("suppMag"),//droit
                new SimpleGrantedAuthority("ROLE_USER"),//role
                new SimpleGrantedAuthority("ROLE_MANAGER"),//role
                new SimpleGrantedAuthority("ROLE_ADMIN"));
    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
            "Abdou Fall", "insa@isepat.edu.sn",roles
    ));
    filterChain.doFilter(request, response);

    }
}
