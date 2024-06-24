package com.RNE.demande.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RNE.demande.entities.Documents;
import com.RNE.demande.repositories.DocumentsRepository;
import com.RNE.demande.services.DocumentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocumentsServiceImpl implements DocumentService{

	private DocumentsRepository documentsRepository;
	
	@Override
	public Documents saveDocument(MultipartFile documentFile) throws IOException {
		Documents fichierDoc=new Documents();
		fichierDoc.setNom(documentFile.getOriginalFilename());
		fichierDoc.setType(documentFile.getContentType());
		
		
		return documentsRepository.save(fichierDoc);
		
	}

	@Override
	public List<Documents> getAllDocuments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocumentsById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteDocuments(Long id) {
		// TODO Auto-generated method stub
		
	}

}
