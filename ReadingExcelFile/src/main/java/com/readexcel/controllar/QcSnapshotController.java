package com.readexcel.controllar;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.readexcel.service.QcSnapshotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class QcSnapshotController {
	
	private QcSnapshotService qcSnapshotService;

	public QcSnapshotController(QcSnapshotService qcSnapshotService) {
		super();
		this.qcSnapshotService = qcSnapshotService;
	}
	
	 @PostMapping("/upload")
	    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
		 log.info("controller entered:  "+file.getName());
	        if (file.isEmpty()) {
	        	 log.info("controller if block entered:  ");
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a valid file.");
	        }

	        try {
	        	 log.info("controller try block entered:  ");
	            // Call the service to process and save the data
	        	qcSnapshotService.saveExcelData(file);
	            return ResponseEntity.status(HttpStatus.OK).body("File uploaded and data processed successfully.");
	        } catch (Exception e) {
	        	 log.info("controller catch block entered:  ");
	            log.error("Error processing file: {}", e.getMessage(), e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the file.");
	        }
	    }

}
