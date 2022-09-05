package pers.idc.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pers.idc.capstone.model.*;
import pers.idc.capstone.repo.BloodRegistryRepository;
import pers.idc.capstone.service.BloodRegistryService;
import pers.idc.capstone.service.UserAuthService;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;

import static pers.idc.capstone.model.Area.AREA_1;
import static pers.idc.capstone.model.BloodGroup.AB_P;
import static pers.idc.capstone.model.State.STATE_A;

@SpringBootApplication
public class CapstoneApplication {
	private final UserAuthService userAuthService;
	private final BloodRegistryService bloodRegistryService;
	private final BloodRegistryRepository bloodRegistryRepository;

	@Autowired
	public CapstoneApplication(
			UserAuthService userAuthService,
			BloodRegistryService bloodRegistryService,
			BloodRegistryRepository bloodRegistryRepository) {
		this.userAuthService = userAuthService;
		this.bloodRegistryService = bloodRegistryService;
		this.bloodRegistryRepository = bloodRegistryRepository;
	}

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

	@PostConstruct
	public void initDemo() {
		BloodRegistryEntity bloodRegistryEntity = new BloodRegistryEntity();
		bloodRegistryEntity.setBloodGroup(AB_P);
		bloodRegistryEntity.setState(STATE_A);
		bloodRegistryEntity.setArea(AREA_1);
		bloodRegistryEntity.setPinCode(123123);
		bloodRegistryEntity.setAvailable(true);
		bloodRegistryEntity.setRequired(false);
		bloodRegistryService.save(bloodRegistryEntity);


		bloodRegistryEntity = new BloodRegistryEntity();
		bloodRegistryEntity.setBloodGroup(AB_P);
		bloodRegistryEntity.setState(STATE_A);
		bloodRegistryEntity.setArea(AREA_1);
		bloodRegistryEntity.setPinCode(123124);
		bloodRegistryEntity.setAvailable(false);
		bloodRegistryEntity.setRequired(false);
		bloodRegistryService.save(bloodRegistryEntity);

		bloodRegistryEntity = new BloodRegistryEntity();
		bloodRegistryEntity.setBloodGroup(AB_P);
		bloodRegistryEntity.setState(STATE_A);
		bloodRegistryEntity.setArea(AREA_1);
		bloodRegistryEntity.setPinCode(123125);
		bloodRegistryEntity.setAvailable(false);
		bloodRegistryEntity.setRequired(true);
		bloodRegistryService.save(bloodRegistryEntity);

		bloodRegistryEntity = new BloodRegistryEntity();
		bloodRegistryEntity.setBloodGroup(AB_P);
		bloodRegistryEntity.setState(STATE_A);
		bloodRegistryEntity.setArea(AREA_1);
		bloodRegistryEntity.setPinCode(123126);
		bloodRegistryEntity.setAvailable(false);
		bloodRegistryEntity.setRequired(true);
		bloodRegistryService.save(bloodRegistryEntity);
	}
}
