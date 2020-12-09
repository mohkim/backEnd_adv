/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kim.advertise.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kim.advertise.Repository.PictureUploadRepository;
import com.kim.advertise.entity.PictureUpload;

/**
 *
 * @author FREE_MIND
 */

@Repository
public class PictureUploadService {

	@Autowired
	public PictureUploadRepository pictureUploadRepository;

	public PictureUpload save(PictureUpload pictureUpload) {
    
		PictureUpload p = pictureUploadRepository.save(pictureUpload);
		if (p != null)
			return p;
		else
			return null;

	}

	public void delete(Long id) {
		pictureUploadRepository.deleteById(id);
	}
	public PictureUpload getByFileName(String name) {
	 return 	pictureUploadRepository.findByName(name);
	}
	public PictureUpload getById(Long id) {
		 return	pictureUploadRepository.findById(id).get();
		}
}
