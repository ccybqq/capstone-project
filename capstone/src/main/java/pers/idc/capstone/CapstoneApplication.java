package pers.idc.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.idc.capstone.model.UserAuth;
import pers.idc.capstone.model.UserEntity;
import pers.idc.capstone.service.UserAuthService;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;

@SpringBootApplication
public class CapstoneApplication {
	@Autowired
	private UserAuthService userAuthService;

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

	@PostConstruct
	public void initUser() {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail("default");
		userEntity.setDateOfBirth(Date.valueOf(LocalDate.now()));
		UserAuth user = new UserAuth();
		user.setUserEntity(userEntity);
		user.setPassword("password");
		userAuthService.register(user);
		userAuthService.makeAdmin(1);
	}
}
