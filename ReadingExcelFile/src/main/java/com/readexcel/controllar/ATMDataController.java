//package com.readexcel.controllar;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.readexcel.service.ATMDataService;
//
//import lombok.extern.slf4j.Slf4j;
//@RestController
//@Slf4j
//public class ATMDataController {
//	
//	
//	private ATMDataService aTMDataService;
//
//	public ATMDataController(ATMDataService aTMDataService) {
//		super();
//		this.aTMDataService = aTMDataService;
//	}
//	
//	
//	  @PostMapping("/upload")
//	    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//		  log.info("controller entered:: "+file.getName());
//	        if (file.isEmpty()) {
//	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
//	        }
//
//	        try {
//	        	aTMDataService.saveExcelData(file);
//	        	log.info("try block entered");
//	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded and data saved successfully");
//	        } catch (Exception e) {
//	        	log.info("catch block entered");
//	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                    .body("Error processing file: " + e.getMessage());
//	        }
//	    }
//
//}
