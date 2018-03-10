package com.redsam;


import com.redsam.dao.CustomerRepository;
import com.redsam.dao.DepartmentsRepository;
import com.redsam.dao.RegionsRepository;
import com.redsam.model.Customer;
import com.redsam.model.Departments;
import com.redsam.model.dto.DepartmentsDTOImpl;
import com.redsam.security.SecurityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.stream.Stream;

import static java.lang.System.exit;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
	RegionsRepository regionsRepository;
    
    
    @Autowired
    DepartmentsRepository departmentsRepository;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public void run(String... args) throws Exception {

        System.out.println("DATASOURCE = " + dataSource);
        
        regionsRepository.count();
        //regionsRepository.findAll();
        
        //Page<DepartmentsDTOImpl> list = departmentsRepository.listDepartments(new PageRequest(0, 10));
        //java.util.Iterator it = list.iterator();
        //while(it.hasNext()) {
        //	DepartmentsDTOImpl val = (DepartmentsDTOImpl)it.next();
       // 	System.out.println(val);
       // }
        
        System.out.println("Done!");

        //exit(0);
    }
    
    /**
	 * Pre-load the system with employees and items.
	 */
	public @PostConstruct void init() {

		
		/**
		 * Due to method-level protections on {@link example.company.ItemRepository}, the security context must be loaded
		 * with an authentication token containing the necessary privileges.
		 */
		SecurityUtils.runAs("Lex", "102", "AD_VP");

		
		//SecurityContextHolder.clearContext();
		
		System.out.println("after init postconstruct....");
	}
	
	/**
	 * This application is secured at both the URL level for some parts, and the method level for other parts. The URL
	 * security is shown inside this code, while method-level annotations are enabled at by
	 * {@link EnableGlobalMethodSecurity}.
	 *
	 */
	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	    @Autowired
	    DataSource dataSource;
	    
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

		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 System.out.println("before configureGlobal...." + dataSource);
					 auth.jdbcAuthentication().dataSource(dataSource)
		  .usersByUsernameQuery(
		   "select first_name, employee_id, 'true' enabled from employees where first_name=?")
		  .authoritiesByUsernameQuery(
		   "select first_name , job_id from employees where first_name=?");
					 
			System.out.println("after configureGlobal....");
		}
		


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
		}*/
		
		@Override
	    public void configure(WebSecurity web) throws Exception {
	        web.debug(true);
	    }
		
		
	}

}