package com.endsound.trpg.security.filter;

import com.endsound.trpg.security.bean.DefaultGrantedAuthority;
import com.endsound.trpg.security.bean.JwtPayload;
import com.endsound.trpg.security.component.JwtComponent;
import com.endsound.trpg.security.exception.TokenExpiredException;
import com.endsound.trpg.security.jooq.tables.pojos.User;
import com.endsound.trpg.security.token.AuthenticationToken;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class TokenFilter extends GenericFilterBean {
    private JwtComponent jwtComponent;

    public TokenFilter(JwtComponent jwtComponent){
        this.jwtComponent = jwtComponent;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try{
            String token = httpServletRequest.getHeader("token");
            if(Objects.nonNull(token)){
                JwtPayload<User> jwtPayload = jwtComponent.parseToken(token);

                AuthenticationToken authenticationToken = new AuthenticationToken(
                        jwtPayload.getData().getUsername(),
                        null,
                        jwtPayload.getRoles().stream().map(role -> new DefaultGrantedAuthority(role)).collect(Collectors.toList())
                );
                authenticationToken.setDetails(jwtPayload.getData());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                Optional.ofNullable(jwtPayload.getRefresh())
                        .filter(Timestamp.from(Instant.now())::after)
                        .ifPresent(time -> {});

                if(Objects.nonNull(jwtPayload.getRefresh()) && Timestamp.from(Instant.now()).after(jwtPayload.getRefresh())){
                    httpServletResponse.setHeader("token", jwtComponent.generateToken(jwtPayload));
                    httpServletResponse.setHeader("Access-Control-Expose-Headers", "token");
                }
            }

        }catch (ExpiredJwtException e){
            throw new TokenExpiredException();
        }catch (SignatureException e){
            throw new TokenExpiredException();
        }catch (IllegalArgumentException e){
            throw new TokenExpiredException();
        }

        chain.doFilter(request, response);
    }
}
