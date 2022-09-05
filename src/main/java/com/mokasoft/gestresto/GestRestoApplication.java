package com.mokasoft.gestresto;

import com.mokasoft.gestresto.entities.AppRole;
import com.mokasoft.gestresto.entities.AppUser;
import com.mokasoft.gestresto.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class GestRestoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestRestoApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
				accountService.addNewRole(new AppRole(null,"ADMIN"));
				accountService.addNewRole(new AppRole(null,"MANAGER"));
				accountService.addNewRole(new AppRole(null,"WAITER"));
				accountService.addNewRole(new AppRole(null,"CASHIER"));


				accountService.addNewUser(new AppUser(null,"waiter1","1234",new ArrayList<>()));
				accountService.addNewUser(new AppUser(null,"admin","1234",new ArrayList<>()));
				accountService.addNewUser(new AppUser(null,"manager","1234",new ArrayList<>()));
				accountService.addNewUser(new AppUser(null,"waiter2","1234",new ArrayList<>()));
				accountService.addNewUser(new AppUser(null,"cashier1","1234",new ArrayList<>()));
				accountService.addNewUser(new AppUser(null,"cashier2","1234",new ArrayList<>()));


			accountService.addRoleToUser("waiter1","WAITER");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("admin","MANAGER");
			accountService.addRoleToUser("admin","WAITER");
			accountService.addRoleToUser("admin","CASHIER");
			accountService.addRoleToUser("manager","MANAGER");
			accountService.addRoleToUser("waiter2","WAITER");
			accountService.addRoleToUser("cashier1","CASHIER");
			accountService.addRoleToUser("cashier2","CASHIER");
		};
	}
}
