package com.cwc.apigateway.service.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.apigateway.service.entities.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	/**
	 * client : using this we can fetch data like email user etc
	 * user: fetch token etc**/
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> loginAuth(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			){
	
		logger.info("User Email Id : {} " , user.getEmail());
		//get collection of authorities
		List<String> authorities = user.getAuthorities().stream().map(grantedAuthorities->{
			return grantedAuthorities.getAuthority();
		}).collect(Collectors.toList());
		
		//Old version without Builder Pattern
//		AuthResponse authResponse = new AuthResponse();
//		authResponse.setUserId(user.getEmail());
//		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
//		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
//		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
//		authResponse.setAuthorities(authorities);
		
		
		//Using Builder Pattern
		AuthResponse authResponseData = AuthResponse.builder()
				.userId(user.getEmail())
				.accessToken(client.getAccessToken().getTokenValue())
				.refreshToken(client.getRefreshToken().getTokenValue())
				.expireAt(client.getAccessToken().getExpiresAt().getEpochSecond())
				.authorities(authorities)
				.build();
		return new ResponseEntity<>(authResponseData,HttpStatus.OK);
		
	}
}
