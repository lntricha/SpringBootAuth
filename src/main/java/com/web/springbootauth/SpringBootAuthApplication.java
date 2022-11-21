package com.web.springbootauth;

import com.web.springbootauth.entity.PrivilegeEntity;
import com.web.springbootauth.entity.RoleEntity;
import com.web.springbootauth.entity.UserEntity;
import com.web.springbootauth.repository.PrivilegeRepository;
import com.web.springbootauth.repository.RoleRepository;
import com.web.springbootauth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@EnableTransactionManagement
@SpringBootApplication
public class SpringBootAuthApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringBootAuthApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuthApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(PrivilegeRepository privilegeRepository,
								  RoleRepository roleRepository,
								  UserRepository userRepository  ) {
		return (args) -> {
			// save a few customers

			PrivilegeEntity priceLookup= new PrivilegeEntity(1L,"Price lookup",null);
			PrivilegeEntity inventoryLookup= new PrivilegeEntity(2L,"Inventory Lookup",null);
			PrivilegeEntity billing= new PrivilegeEntity(3L,"Billing",null);
			PrivilegeEntity returnMerchandise= new PrivilegeEntity(4L,"Return Merchandise",null);
			PrivilegeEntity exchangeMerchandise= new PrivilegeEntity(5L,"Exchange Merchandise",null);
			PrivilegeEntity storeOpen= new PrivilegeEntity(6L,"Store open",null);
			PrivilegeEntity storeClose= new PrivilegeEntity(7L,"Store close",null);
			PrivilegeEntity priceOverride= new PrivilegeEntity(8L,"Price override",null);
			PrivilegeEntity inventoryUpdate= new PrivilegeEntity(9L,"Inventory update",null);

			priceLookup=privilegeRepository.save(priceLookup);
			inventoryLookup=privilegeRepository.save(inventoryLookup);
			billing=privilegeRepository.save(billing);
			returnMerchandise=privilegeRepository.save(returnMerchandise);
			exchangeMerchandise=privilegeRepository.save(exchangeMerchandise);
			storeOpen=privilegeRepository.save(storeOpen);
			storeClose=privilegeRepository.save(storeClose);
			priceOverride=privilegeRepository.save(priceOverride);
			inventoryUpdate=privilegeRepository.save(inventoryUpdate);

			Set<PrivilegeEntity> privilegeEntities= new HashSet<>();
			privilegeEntities.add(priceLookup);
			privilegeEntities.add(inventoryLookup);

			RoleEntity storeAssociate= new RoleEntity(1L,"Store Associate",privilegeEntities);
			Set<RoleEntity> roleEntitySet=new HashSet<>();
			roleEntitySet.add(storeAssociate);
			priceLookup.setRoles(roleEntitySet);
			inventoryLookup.setRoles(roleEntitySet);
			storeAssociate=roleRepository.save(storeAssociate);
			UserEntity user= new UserEntity(1L,"John","Smith", "JohnSmith@store.com",storeAssociate);
			UserEntity user1= new UserEntity(5L,"Sarah","s", "SarahS@store.com",storeAssociate);

			Set<UserEntity> userEntitySet=new HashSet<>();
			userEntitySet.add(user);
			userEntitySet.add(user1);
			storeAssociate.setUsers(userEntitySet);
			userRepository.save(user);
			userRepository.save(user1);

			privilegeEntities.add(billing);
			privilegeEntities.add(returnMerchandise);
			privilegeEntities.add(exchangeMerchandise);
			RoleEntity cashier= new RoleEntity(2L,"Cashier",privilegeEntities);
			cashier=roleRepository.save(cashier);
			 user= new UserEntity(2L,"Jane","Doe", "JaneDoe@store.com",cashier);
			userEntitySet=new HashSet<>();
			userEntitySet.add(user);
			cashier.setUsers(userEntitySet);
			userRepository.save(user);

			privilegeEntities.add(storeOpen);
			privilegeEntities.add(storeClose);
			privilegeEntities.add(priceOverride);
			RoleEntity manager= new RoleEntity(3L,"Manager",privilegeEntities);
			manager=roleRepository.save(manager);
			 user= new UserEntity(3L,"Chris","Jacob", "ChrisJacob@store.com",manager);
			userEntitySet=new HashSet<>();
			userEntitySet.add(user);
			manager.setUsers(userEntitySet);
			userRepository.save(user);



			privilegeEntities.add(storeOpen);
			privilegeEntities.add(storeClose);
			privilegeEntities.add(priceOverride);
			privilegeEntities.add(inventoryUpdate);
			RoleEntity admin= new RoleEntity(4L,"Admin",privilegeEntities);
			admin=roleRepository.save(admin);
			user= new UserEntity(4L,"Tim","Brady", "TimBrady@store.com",admin);
			userEntitySet=new HashSet<>();
			userEntitySet.add(user);
			manager.setUsers(userEntitySet);
			userRepository.save(user);

		};
	}




}
