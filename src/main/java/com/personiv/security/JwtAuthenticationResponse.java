package com.personiv.security;

import java.io.Serializable;
import java.util.Date;

import com.personiv.model.User;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String access_token;
    private final String token_type;
    private final Date expiration;
    private  User user;
    
    public JwtAuthenticationResponse(String access_token,Date expiration,String token_type) {
        this.access_token = access_token;
        this.expiration = expiration;
        this.token_type = token_type;
    }
    public JwtAuthenticationResponse(String access_token,Date expiration,String token_type,User user) {
        this.access_token = access_token;
        this.expiration = expiration;
        this.token_type = token_type;
        this.user = user;
    }
    public String getAccess_token() {
        return access_token;
    }
    public String getToken_type() {
    	return token_type;
    }
    public Date getExpiration() {
    	return expiration;
    }
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}

