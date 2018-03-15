package com.redsam.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * This application is secured at both the URL level for some parts, and the method level for other parts. The URL
 * security is shown inside this code, while method-level annotations are enabled at by
 * {@link EnableGlobalMethodSecurity}.
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    
    
    @Value("${security.signing-key}")
    private String signingKey;

    
    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    
    @Value("${security.security-realm}")
    private String securityRealm;
    
    
    @Autowired
    private UserDetailsService userDetailsService;
    

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
       return super.authenticationManager();
    }
    
    
    /**
	 * This section defines the user accounts which can be used for authentication as well as the roles each user has.
	 */
	/*@Bean
	InMemoryUserDetailsManager userDetailsManager() {

		
		
		return new InMemoryUserDetailsManager
				User
					.withUsername("user")
					.password("password")
					.roles("USER").build());
	}*/
	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin").password("admin").roles("USER","ADMIN");
		System.out.println("after configureGlobal....");
	}*/

	
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 System.out.println("before configureGlobal...." + dataSource);
				 auth.jdbcAuthentication().dataSource(dataSource)
	  .usersByUsernameQuery(
	   "select first_name, employee_id, 'true' enabled from employees where first_name=?")
	  .authoritiesByUsernameQuery(
	   "select first_name , job_id from employees where first_name=?");
				 
		System.out.println("after configureGlobal....");
	}
	*/


	/**
	 * This section defines the security policy for the app.
	 * <p>
	 * <ul>
	 * <li>BASIC authentication is supported (enough for this REST-based demo).</li>
	 * <li>/employees is secured using URL security shown below.</li>
	 * <li>CSRF headers are disabled since we are only testing the REST interface, not a web one.</li>
	 * </ul>
	 * NOTE: GET is not shown which defaults to permitted.
	 *
	 * @param http
	 * @throws Exception
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().//
				antMatchers(HttpMethod.POST, "/countries").hasRole("AD_VP").//
				antMatchers(HttpMethod.PUT, "/departments/**").hasRole("AD_VP").//
				antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("AD_VP").and().//
				csrf().disable();
	}
	
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService)
               .passwordEncoder(new ShaPasswordEncoder(encodingStrength));
    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .httpBasic()
               .realmName(securityRealm)
               .and()
               .csrf()
               .disable();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
       JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
       converter.setSigningKey(signingKey);
       return converter;
    }

    @Bean
    public TokenStore tokenStore() {
       return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary //Making this primary to avoid any accidental duplication with another token service instance of the same name
    public DefaultTokenServices tokenServices() {
       DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
       defaultTokenServices.setTokenStore(tokenStore());
       defaultTokenServices.setSupportRefreshToken(true);
       return defaultTokenServices;
    }
}