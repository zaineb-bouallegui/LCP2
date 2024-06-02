package com.RNE.demande.controllers;

import java.util.Base64;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RNE.demande.entities.Documents;
import com.RNE.demande.repositories.DocumentsRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/documents")
@AllArgsConstructor
public class DocumentsController {

	private DocumentsRepository documentRepository;

	
	
	
	
	 
	 //download2
	 @GetMapping("/download/{id}")
	 public ResponseEntity<ByteArrayResource> downloadDocuments(@PathVariable Long id) {
	     Optional<Documents> optionalDocument = documentRepository.findById(id);
	     if (optionalDocument.isPresent()) {
	         Documents document = optionalDocument.get();
	         String filename = document.getNom();
	         byte[] content = Base64.getDecoder().decode(document.getContenu());

	         ByteArrayResource resource = new ByteArrayResource(content);

	         // Get the file type
	         String fileType = document.getType();

	         
	         MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
	         if (fileType != null && !fileType.isEmpty()) {
	             mediaType = MediaType.parseMediaType(fileType);
	         }

	         return ResponseEntity.ok()
	                 .contentType(mediaType)
	                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
	                 .body(resource);
	     } else {
	         return ResponseEntity.notFound().build();
	     }
	 }
 }


    
		
	