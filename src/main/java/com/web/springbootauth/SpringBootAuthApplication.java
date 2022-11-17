package com.web.springbootauth;

import com.web.springbootauth.entity.PrivilegeEntity;
import com.web.springbootauth.entity.RoleEntity;
import com.web.springbootauth.entity.UserEntity;
import com.web.springbootauth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

@EnableTransactionManagement
@SpringBootApplication
public class SpringBootAuthApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootAuthApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuthApplication.class, args);
	}






	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few customers

			PrivilegeEntity priceLookup= new PrivilegeEntity("Price lookup");
			PrivilegeEntity inventoryLookup= new PrivilegeEntity("Inventory Lookup");
			PrivilegeEntity billing= new PrivilegeEntity("Billing");
			PrivilegeEntity returnMerchandise= new PrivilegeEntity("Return Merchandise");
			PrivilegeEntity exchangeMerchandise= new PrivilegeEntity("Exchange Merchandise");
			PrivilegeEntity storeOpen= new PrivilegeEntity("Store open");
			PrivilegeEntity storeClose= new PrivilegeEntity("Store close");
			PrivilegeEntity priceOverride= new PrivilegeEntity("Price override");

			ArrayList<PrivilegeEntity> privilegeEntities= new ArrayList<>();
			privilegeEntities.add(priceLookup);
			privilegeEntities.add(inventoryLookup);

			RoleEntity storeAssociate= new RoleEntity("Store Associate",privilegeEntities);
			UserEntity user1= new UserEntity("John","Smith", "JohnSmith@store.com",storeAssociate);
			repository.save(user1);

			privilegeEntities.add(billing);
			privilegeEntities.add(returnMerchandise);
			privilegeEntities.add(exchangeMerchandise);
			RoleEntity cashier= new RoleEntity("Cashier",privilegeEntities);
			UserEntity user2= new UserEntity("Jane","Doe", "JaneDoe@store.com",cashier);
			repository.save(user2);

			privilegeEntities.add(storeOpen);
			privilegeEntities.add(storeClose);
			privilegeEntities.add(priceOverride);
			RoleEntity manager= new RoleEntity("Manager",privilegeEntities);
			UserEntity user3= new UserEntity("Chris","Jacob", "ChrisJacob@store.com",manager);
			repository.save(user3);



		};
	}




}
