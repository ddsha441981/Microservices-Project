package com.cwc.apigateway.service.entities;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
	
	private String userId;
	private String accessToken;
	private String refreshToken;
	private long expireAt;
	private Collection<String> authorities;

}
