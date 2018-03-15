package com.redsam;

//https://medium.com/@nydiarra/secure-a-spring-boot-rest-api-with-json-web-token-reference-to-angular-integration-e57a25806c50

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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

}