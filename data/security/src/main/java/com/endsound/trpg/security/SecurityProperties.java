package com.endsound.trpg.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com.endsound.trpg.security")
public class SecurityProperties {
    private Cors cors = new Cors();
    private Jwt jwt = new Jwt();

    public final class Cors{
        private List<String> allowedOrigins;

        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }

        public Cors setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
            return this;
        }
    }

    public final class Jwt{
        private String key;
        private Integer expirationTime;
        private Integer refreshTime;

        public String getKey() {
            return key;
        }

        public Jwt setKey(String key) {
            this.key = key;
            return this;
        }

        public Integer getExpirationTime() {
            return expirationTime;
        }

        public Jwt setExpirationTime(Integer expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public Integer getRefreshTime() {
            return refreshTime;
        }

        public Jwt setRefreshTime(Integer refreshTime) {
            this.refreshTime = refreshTime;
            return this;
        }
    }

    public Cors getCors() {
        return cors;
    }

    public SecurityProperties setCors(Cors cors) {
        this.cors = cors;
        return this;
    }

    public Jwt getJwt() {
        return jwt;
    }

    public SecurityProperties setJwt(Jwt jwt) {
        this.jwt = jwt;
        return this;
    }
}
