package com.kim.advertise.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kim.advertise.Service.SpecificationHeadOptionService;
import com.kim.advertise.Service.SpecificationHeadService;
import com.kim.advertise.entity.SpecificationHead;
import com.kim.advertise.entity.SpecificationHeadOption;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin/")

public class SpecificationHeaderOptionResource {

	@Autowired
	private SpecificationHeadService  speHService   ;
	@Autowired
	private SpecificationHeadOptionService  spHeaOptionService  ;
 

	@GetMapping("head_optionlist/{id}")
	public List<SpecificationHeadOption> getAllSpecificationHeadOption(@PathVariable Long id) {
		SpecificationHead speHead = speHService.getSpecificationHead(id);

		return speHead.getOption();
	}

	@GetMapping("/head_option/{id}")
	public SpecificationHeadOption getSingleSpecificationHeadOption(@PathVariable Long id) {

		return  this.spHeaOptionService.getSpecificationHeadOption(id);

	}

	@DeleteMapping("head_option/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteSpecificationHeadOption(@PathVariable Long id) {
		SpecificationHeadOption spHO = spHeaOptionService.getSpecificationHeadOption(id);
   
		   if(spHO!= null) {
			   spHeaOptionService.removeSpecificationHeadOption(id);  
			   return ResponseEntity.ok(new MessageResponse("  option  deleted Successfully !!!"));
		   }else {
			   return ResponseEntity.badRequest().body(new MessageResponse(" Option deleted failled  !!!"));
		   }
		
	}

	@PostMapping("/specification_head/{id}/head_option")
 	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> saveSpecificationOption(@PathVariable Long id,@Valid @RequestBody SpecificationHeadOption spHeadO) {
		 
		SpecificationHead  spH = speHService.getSpecificationHead(id);

		if (spH != null) {
			
			SpecificationHeadOption  opt2=spHeaOptionService.save(spHeadO);
			
			spH.addOption(opt2);

			if (speHService.save(spH) != null) {

				return ResponseEntity.ok(new MessageResponse("  Option saved Successfully !!!"));

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("  Option save  failed !!!"));

			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("  Specification Head not Found !!"));
		}

	}
}
