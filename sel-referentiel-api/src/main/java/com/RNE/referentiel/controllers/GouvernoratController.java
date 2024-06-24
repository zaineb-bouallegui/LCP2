package com.RNE.referentiel.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RNE.referentiel.dto.GouvernoratDTO;
import com.RNE.referentiel.services.GouvernoratService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/referentiel/gouvernorats")
@AllArgsConstructor
public class GouvernoratController {

	private final GouvernoratService gouvernoratService;

	@PostMapping
	@PreAuthorize("hasRole('client_admin')")
	public ResponseEntity<GouvernoratDTO> saveDelegation(@RequestBody GouvernoratDTO gouvernoratDTO) {
		return new ResponseEntity<GouvernoratDTO>(gouvernoratService.saveGouvernorat(gouvernoratDTO), HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize("hasRole('client_user')")
	public ResponseEntity<List<GouvernoratDTO>> getAllDelegations() {
		return new ResponseEntity<List<GouvernoratDTO>>(gouvernoratService.getAllGouvernorats(), HttpStatus.OK);
	}

	// get delegation by code
	@GetMapping("/{code}")
	public ResponseEntity<GouvernoratDTO> getDelegationByCode(@PathVariable String code) {
		return new ResponseEntity<GouvernoratDTO>(gouvernoratService.getGouvernoratByCode(code), HttpStatus.OK);
	}

	// update delegation
	@PutMapping("/update/{code}")
	public ResponseEntity<GouvernoratDTO> updateDelegation(@PathVariable String code,
			@RequestBody GouvernoratDTO gouvernoratDTO) {
		return new ResponseEntity<GouvernoratDTO>(gouvernoratService.updateGouvernorat(code, gouvernoratDTO),
				HttpStatus.OK);
	}

	// getActivate delegation
	@GetMapping("/Activated")
	public ResponseEntity<List<GouvernoratDTO>> getActivatedDelegations() {
		return new ResponseEntity<List<GouvernoratDTO>>(gouvernoratService.getActivatedGouvernorats(), HttpStatus.OK);
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<String> deleteDelegation(@PathVariable String code) {
		gouvernoratService.deleteGouvernorat(code);
		return new ResponseEntity<String>("Gouvernorat successfully deleted!", HttpStatus.OK);
	}

}