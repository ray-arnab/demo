package com.company.demo.client;

public class OauthToken {
	
	private String access_token;
	private String token_type;
	private long expires_in;
	private String scope;
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Token {")
			.append("access_token=").append(access_token)
			.append(", expires_in='").append(expires_in)
                .append("'}");
		return sb.toString();
    }
}
