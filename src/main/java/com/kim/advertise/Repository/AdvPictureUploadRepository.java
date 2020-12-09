package com.kim.advertise.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kim.advertise.entity.PictureUpload;

 

@Repository
public interface AdvPictureUploadRepository extends JpaRepository<PictureUpload, Long> {
	 
}
