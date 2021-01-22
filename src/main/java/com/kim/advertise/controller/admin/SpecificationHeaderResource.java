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

import com.kim.advertise.Service.ProductSubCatagoryService;
import com.kim.advertise.Service.SpecificationHeadService;
import com.kim.advertise.entity.ProductSubCatagory;
import com.kim.advertise.entity.SpecificationHead;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin")

public class SpecificationHeaderResource {

	@Autowired
	private ProductSubCatagoryService  subCatagoryService ;
	@Autowired
	private SpecificationHeadService  speHeadService;
 

	@GetMapping("/subcatagory/{id}/specification_head")
	public List<SpecificationHead> getAllSpecificationHead(@PathVariable Long id) {
		ProductSubCatagory pSubCat = subCatagoryService.getProductSubCatagory(id);

		return pSubCat.getSpecificationList();
	}

	@GetMapping("/specification_head/{id}")
	public SpecificationHead getSingleSpecificationHead(@PathVariable Long id) {

		return  this.speHeadService.getSpecificationHead(id);

	}

	@DeleteMapping("/specification_head/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteSpecificationHead(@PathVariable Long id) {
		SpecificationHead sp = speHeadService.getSpecificationHead(id);
   
		   if(sp!= null) {
			   speHeadService.removeSpecificationHead(id);  
			   return ResponseEntity.ok(new MessageResponse("  SpecificationHead deleted Successfully !!!"));
		   }else {
			   return ResponseEntity.badRequest().body(new MessageResponse(" SpecificationHead deleted failled  !!!"));
		   }
		
	}

	@PostMapping("/subcatagory/{id}/specification_head")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> saveSubCatagory(@PathVariable Long id,@Valid @RequestBody SpecificationHead spHead) {
		 
		ProductSubCatagory  pSc = subCatagoryService.getProductSubCatagory(id);
       
		  
		
		if (pSc != null) {
			
			SpecificationHead  sphead2=speHeadService.save(spHead);
			
			
			  pSc.addSpecificationList(sphead2);
			
             			
			if (subCatagoryService.save(pSc) != null) {

				return ResponseEntity.ok(new MessageResponse("  Specification Head saved Successfully !!!"));

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("  Specification Head save  failed !!!"));

			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("  Product Sub Catagory not Found !!"));
		}

	}
}
