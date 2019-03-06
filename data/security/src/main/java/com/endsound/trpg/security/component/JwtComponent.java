package com.endsound.trpg.security.component;

import com.endsound.trpg.security.SecurityProperties;
import com.endsound.trpg.security.bean.DefaultGrantedAuthority;
import com.endsound.trpg.security.bean.JwtPayload;
import com.endsound.trpg.security.jooq.tables.pojos.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtComponent {
    private SecurityProperties.Jwt jwtProperties;

    public JwtComponent(SecurityProperties.Jwt jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(JwtPayload jwtPayload){
        Timestamp current = Timestamp.from(Instant.now());

        Claims claims = Jwts.claims()
                .setSubject(jwtPayload.getSubject())
                .setAudience(jwtPayload.getAudience())
                .setIssuer(jwtPayload.getIssuer())
                .setIssuedAt(current);

        Optional.ofNullable(jwtProperties.getExpirationTime())
                .ifPresent(expirationTime -> {
                    Timestamp expiration = new Timestamp(current.getTime() + expirationTime * 60 * 1000);
                    claims.setExpiration(expiration);

                    Optional.ofNullable(jwtProperties.getRefreshTime())
                            .ifPresent(refreshTime -> {
                                Timestamp refresh = new Timestamp(current.getTime() + (expirationTime - refreshTime) * 60 * 1000);
                                claims.put("refresh", refresh);
                            });
                });



        //set payload
        claims.put("data", jwtPayload.getData());
        claims.put("roles", jwtPayload.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getKey())
                .compact();
    }

    public JwtPayload parseToken(String token) throws IOException {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getKey())
                .parseClaimsJws(token)
                .getBody();

        JwtPayload jwtPayload = new JwtPayload()
                .setSubject(claims.getSubject())
                .setAudience(claims.getAudience())
                .setIssuer(claims.getIssuer())
                .setIssuedAt(new Timestamp(claims.getIssuedAt().getTime()));

        if(Objects.nonNull(jwtPayload.getExpiration())){
            jwtPayload.setExpiration(new Timestamp(claims.getExpiration().getTime()));

            if(Objects.nonNull(jwtPayload.getRefresh()))
                jwtPayload.setRefresh(new Timestamp((Long)claims.get("refresh")));
        }

        ObjectMapper objectMapper = new ObjectMapper();

        jwtPayload.setData(objectMapper.readValue(objectMapper.writeValueAsString(claims.get("data")), User.class))
                .setRoles(((List<String>)claims.get("roles")));
        return jwtPayload;
    }
}
