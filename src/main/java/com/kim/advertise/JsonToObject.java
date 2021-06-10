package com.kim.advertise;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.advertise.Service.ProductCatagoryService;
import com.kim.advertise.Service.ProductSubCatagoryService;
import com.kim.advertise.Service.SpecificationHeadOptionService;
import com.kim.advertise.Service.SpecificationHeadService;
import com.kim.advertise.entity.*;

@Component
public class JsonToObject {

	@Autowired
	private ProductCatagoryService catService;

	@Autowired
	private ProductSubCatagoryService subCatService;

	@Autowired
	private SpecificationHeadService specificationHeadService;

	@Autowired
	private SpecificationHeadOptionService SpecificationHeadService;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Transactional
	public void jsonFilesToDatabase() {
		if (catService.getAllProductCatagory().isEmpty()) {
			jsonToDataBase("src\\json\\vehicle.json");
		}

	}

	public void jsonToDataBase(String fileName) {

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// read json file and convert to customer object
		try {
			ProductCatagory cat = objectMapper.readValue(new File(fileName), ProductCatagory.class);

			List<ProductSubCatagory> subcatlist = cat.getProductSubcatagory();
			cat = catService.save(cat);
			for (ProductSubCatagory scat : subcatlist) {
				scat.setProductCatagory(cat);
				scat.removeSpaceFromSpecificationHead();
				ProductSubCatagory subCatagory = subCatService.save(scat);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
