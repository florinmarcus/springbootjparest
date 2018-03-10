package com.redsam;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.redsam.model.*;

@Configuration
public class RestConfiguration  {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", config);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

      return new RepositoryRestConfigurerAdapter() {

        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
          config.exposeIdsFor(Regions.class);
          config.exposeIdsFor(Locations.class);
          config.exposeIdsFor(Jobs.class);
          config.exposeIdsFor(Employees.class);
          config.exposeIdsFor(Departments.class);
          config.exposeIdsFor(Customer.class);
          config.exposeIdsFor(Countries.class);
        }
      };
    }
}