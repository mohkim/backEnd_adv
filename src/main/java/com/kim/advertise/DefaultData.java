package com.kim.advertise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kim.advertise.Service.CurrencyService;
import com.kim.advertise.Service.RoleService;
import com.kim.advertise.Service.SalesLocationService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.Currency;
import com.kim.advertise.entity.Role;
import com.kim.advertise.entity.SalesLocation;
import com.kim.advertise.entity.User;
import com.kim.advertise.entity.emum.ERole;
@Component
public class DefaultData {

	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private  SalesLocationService  salesLocationService;
	@Autowired 
	private  CurrencyService  currService;
	 
	public void  setData() {
		 setRoles();
		 setUser();
		 setCurrency();
		 setLocation();
		 
	}
	private void setRoles() {

		if (roleService.allRole().isEmpty()) {
			roleService.save(new Role(ERole.ROLE_USER));
			roleService.save(new Role(ERole.ROLE_MANAGER));
			roleService.save(new Role(ERole.ROLE_FINANCE));
			roleService.save(new Role(ERole.ROLE_ADMIN));

		}
	}

	private void setUser() {

		if (userService.allUser().isEmpty()) {
			User u = new User("kemal Mohammed", "dawa.age2020@gmail.com", "ll123456!");
			u.addRole(roleService.getRoleByName(ERole.ROLE_USER));
			u.addRole(roleService.getRoleByName(ERole.ROLE_ADMIN));
			u.addRole(roleService.getRoleByName(ERole.ROLE_FINANCE));
			u.addRole(roleService.getRoleByName(ERole.ROLE_MANAGER));
			u.encodePassword();
			u.setActive(true);
			u.setDisabledbyAdmin(false);
			u.setFullName("kemal Mohammed");

			userService.save(u);
		}

	}
	private  void setLocation() {
		if(salesLocationService.getAllLocation().isEmpty()) {
		    salesLocationService.save(new SalesLocation("Juba","Jubek","South Sudan"));
		    salesLocationService.save(new SalesLocation("Yey","Jongli State","South Sudan"));
		    salesLocationService.save(new SalesLocation("Yambio","Central Equatorial","South Sudan"));
		}
	}
	private void setCurrency() {
		if(currService.getAllCurrency().isEmpty()) {
			currService.save(new Currency("SOUTH SUDAN POUND","SSP"));
			currService.save(new Currency("UNITED STATES DOLLAR","USD"));
		}
		
	}
	private  void setCatagory() {
		
	}
}
