package com.kim.advertise.controller.Finance;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Service.DepositeReciptService;
import com.kim.advertise.Service.UserService;
import com.kim.advertise.entity.DepositReciept;
import com.kim.advertise.jwt.MessageResponse;

 
@RestController
@RequestMapping("/adv/fin")
//@PreAuthorize("hasRole('ROLE_MANAGER')")

public class DepositReceiptResource {

	@Autowired
	private   DepositeReciptService depositeReciptService;
	@Autowired
	private UserService  userService;
	
	final static Logger logger = LoggerFactory.getLogger(DepositReceiptResource.class);

	
	@GetMapping("/finance/receipt")
	public List<DepositReciept> getAllDepositeReceipt() {
		return depositeReciptService.getAllDepositeReceipt();
	}
	@GetMapping("/finance/receipt/{id}")
	public DepositReciept getDepositeReceiptById(@PathVariable Long  id  ) {
		return depositeReciptService.getDepositeRecipt(id);
	}
	
	@GetMapping("/finance/receipt/user/{user_id}")
	public List<DepositReciept> getAllDepositeReceiptUser(@PathVariable Long  user_id  ) {
		return depositeReciptService.getAllDepositeReceiptByUser(user_id);
	}
	@GetMapping("/finance/receipt/casher/{user_id}/start/{start}/end/{end}")
	public List<DepositReciept> getAllDepositeReceiptCasherByDate(@PathVariable Long  user_id ,
			             @PathVariable(name = "start") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime start ,
			             @PathVariable(name = "end") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime end) {
		
 
		return depositeReciptService.getAllDepositeReceiptByCasherByDate(user_id, start, end);
 }
 
	@GetMapping("/finance/receipt/casher/{casher_id}")
	public List<DepositReciept> getAllDepositeReceiptByCasher(@PathVariable Long  casher_id  ) {
		return depositeReciptService.getAllDepositeReceiptByCasher(casher_id);
	}
 
	 
	
	@PostMapping("/finance/receipt/user/{user_id}/casher/{casher_id}")
	public   ResponseEntity<?>  saveDepositeReceipt(@PathVariable Long user_id,
			                          @PathVariable Long casher_id ,
			                          @RequestBody DepositReciept deReciept ) {
	 
		deReciept.setUser_casher(userService.getUser(casher_id));
		deReciept.setUser(userService.getUser(user_id));
		 
		
         logger.info("receipt data =>"+deReciept);
 
    
	if (depositeReciptService.save(deReciept)!= null) {
		return ResponseEntity.ok(new MessageResponse("Receipt Accepted   Successfully !!!"));
	} else {
		
	 return ResponseEntity.badRequest().body(new MessageResponse(" Receipt Accepted failed !!!"));
        
	   }
         }
 
 }
