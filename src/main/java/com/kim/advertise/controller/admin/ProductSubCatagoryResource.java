package com.kim.advertise.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.kim.advertise.Service.ProductCatagoryService;
import com.kim.advertise.Service.ProductSubCatagoryService;
import com.kim.advertise.entity.ProductCatagory;
import com.kim.advertise.entity.ProductSubCatagory;
import com.kim.advertise.jwt.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/adv/admin/")

public class ProductSubCatagoryResource {

	@Autowired
	private ProductSubCatagoryService catSubService;
	@Autowired
	private ProductCatagoryService catService;
	private Logger log = LoggerFactory.getLogger(this.getClass());

 
	@GetMapping("subcatagorylist/{id}")
	public List<ProductSubCatagory> getAllProductSubcatagory(@PathVariable Long id) {

	return  catService.getProductCatagory(id).getProductSubcatagory();

	}

	@GetMapping("subcatagory/{id}")
	public ProductSubCatagory getSingleSubCatagory(@PathVariable Long id) {

		return catSubService.getProductSubCatagory(id);

	}

	@DeleteMapping("subcatagory/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteSubCatagory( @PathVariable Long id) {
	 
 
			ProductSubCatagory pSc = catSubService.getProductSubCatagory(id);
			if(pSc!=null) {
				catSubService.remoSubCatagory(id);
				return ResponseEntity.ok(new MessageResponse("  Sub Catagory deleted Successfully !!!"));
			}else {
				return ResponseEntity.badRequest().body(new MessageResponse(" Sub Catagory Not Found  !!!"));
				
			} 
	}

	@PostMapping("/catagory/{id}/subcatagory")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> saveSubCatagory(@PathVariable Long id,@Valid @RequestBody ProductSubCatagory pSc) {
		log.info("Sub catagory is kemal ->"+pSc.toString());
		ProductCatagory pc = catService.getProductCatagory(id);

		if (pc != null) {
			ProductSubCatagory p = catSubService.save(pSc);
                 pc.addProductSubcatagory(p);			
			if (catService.save(pc) != null) {

				return ResponseEntity.ok(new MessageResponse("  Sub Catagory Saved Successfully !!!"));

			} else {
				return ResponseEntity.badRequest().body(new MessageResponse(" Sub Catagory save  failed !!!"));

			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("  Catagory not Found !!"));
		}

	}
}
