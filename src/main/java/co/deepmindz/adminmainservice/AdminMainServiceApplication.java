package co.deepmindz.adminmainservice;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "co.deepmindz.adminmainservice.repository")
@ComponentScan(basePackages = "co.deepmindz.adminmainservice")
public class AdminMainServiceApplication extends SpringBootServletInitializer{

	@Bean
	@LoadBalanced
	RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AdminMainServiceApplication.class, args);
	}
}
