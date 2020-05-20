package com.ad.app.Controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AppController {
	
	@Autowired
    private TokenStore tokenStore;
	
	@Bean
	public TokenStore tokenStore() {
	    return new InMemoryTokenStore();
	}

	@RequestMapping(value = "/")
	public ModelAndView home()
	{
		return new ModelAndView("homePage");
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView loginPage()
	{
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/logoutSuccess")
	public ModelAndView logoutPage()
	{
		return new ModelAndView("logout");
	}
	
	@RequestMapping(value = "/oauth/revoke-token")
    public ModelAndView logout(HttpServletRequest request) 
	{
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) 
        {
        	try
        	{
	        	System.out.println(authHeader);
	            String tokenValue = authHeader.replace("Bearer", "").trim();
	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
	            tokenStore.removeAccessToken(accessToken);
        	}catch (Exception e) {
				System.out.println(e);
			}
        }
        return new ModelAndView("logout");
    }
	
	@GetMapping(path = "/user")
	@ResponseBody public Principal user(Principal principal)
	{
		return principal;
	}
	
}
