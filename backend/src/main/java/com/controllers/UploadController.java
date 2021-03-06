package com.controllers;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
 
import com.services.StorageService;
 
@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class UploadController {
 
	@Autowired
	StorageService storageService;
 
	List<String> files = new ArrayList<String>();
	
	@PostMapping("/upload")
	public ResponseEntity<Object>  handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());
		} catch (Exception e) {
			System.out.println("FAIL to upload picture");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
	}	
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline")
				.body(file);
	}
}
