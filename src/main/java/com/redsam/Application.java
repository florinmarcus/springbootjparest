package com.redsam;


import com.redsam.dao.CustomerRepository;
import com.redsam.dao.DepartmentsRepository;
import com.redsam.dao.RegionsRepository;
import com.redsam.model.Customer;
import com.redsam.model.Departments;
import com.redsam.model.dto.DepartmentsDTOImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
        
        //regionsRepository.count();
        //regionsRepository.findAll();
        
        Page<DepartmentsDTOImpl> list = departmentsRepository.listDepartments(new PageRequest(0, 10));
        java.util.Iterator it = list.iterator();
        while(it.hasNext()) {
        	DepartmentsDTOImpl val = (DepartmentsDTOImpl)it.next();
        	System.out.println(val);
        }
        

//        System.out.println("\n1.findAll()...");
//        for (Customer customer : customerRepository.findAll()) {
//            System.out.println(customer);
//        }
//
//        System.out.println("\n2.findByEmail(String email)...");
//        for (Customer customer : customerRepository.findByEmail("222@yahoo.com")) {
//            System.out.println(customer);
//        }
//
//        System.out.println("\n3.findByDate(Date date)...");
//        for (Customer customer : customerRepository.findByDate(sdf.parse("2017-02-12"))) {
//            System.out.println(customer);
//        }
//
//        // For Stream, need @Transactional
//        System.out.println("\n4.findByEmailReturnStream(@Param(\"email\") String email)...");
//        try (Stream<Customer> stream = customerRepository.findByEmailReturnStream("333@yahoo.com")) {
//            stream.forEach(x -> System.out.println(x));
//        }

        System.out.println("Done!");

        //exit(0);
    }

}