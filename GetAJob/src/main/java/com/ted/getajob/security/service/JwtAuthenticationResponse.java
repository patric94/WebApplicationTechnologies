package com.ted.getajob.security.service;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;



    private final String url;

    public JwtAuthenticationResponse(String token, String url) {
        this.token = token;
        this.url = url;
    }

    public String getToken() {
        return this.token;
    }
    public String getUrl() { return url; }
}

