package com.RNE.demande.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.RNE.demande.entities.Documents;

public interface DocumentService {

	public Documents saveDocument(MultipartFile documentFile) throws IOException;

	public List<Documents> getAllDocuments();

	public Documents getDocumentsById(Long id);

	public void deleteDocuments(Long id);

}
