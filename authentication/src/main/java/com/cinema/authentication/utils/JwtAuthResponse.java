package com.cinema.authentication.utils;

public class JwtAuthResponse {
    private String token;
    private String type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JwtAuthResponse() {
        super();
    }

    public JwtAuthResponse(String token, String type) {
        this.token = token;
        this.type = type;
    }

    @Override
    public String toString() {
        return "JwtAuthResponse{" +
                "token='" + token + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
