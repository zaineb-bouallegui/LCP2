package com.RNE.referentiel.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RNE.referentiel.dto.CodePostalDTO;
import com.RNE.referentiel.services.CodePostalService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/referentiel/Codepostal")
@AllArgsConstructor
public class CodePostalController {

	private CodePostalService codePostalService;

	@PostMapping
	public ResponseEntity<CodePostalDTO> saveCodePostal(@RequestBody CodePostalDTO codePostalDTO) {
		return new ResponseEntity<CodePostalDTO>(codePostalService.saveCodePostal(codePostalDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CodePostalDTO>> getAllCodesPostal() {
		return new ResponseEntity<List<CodePostalDTO>>(codePostalService.getAllCodePostal(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CodePostalDTO> getCodePostalById(@PathVariable Long id) {
		return new ResponseEntity<CodePostalDTO>(codePostalService.getCodePostalById(id), HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CodePostalDTO> updateCodePostal(@PathVariable Long id,
			@RequestBody CodePostalDTO codePostalDTO) {
		return new ResponseEntity<CodePostalDTO>(codePostalService.updateCodePostal(id, codePostalDTO), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCodePostal(@PathVariable Long id) {
		codePostalService.deleteCodePostal(id);
		return new ResponseEntity<String>("CodePostal successfully deleted!", HttpStatus.OK);
	}
}