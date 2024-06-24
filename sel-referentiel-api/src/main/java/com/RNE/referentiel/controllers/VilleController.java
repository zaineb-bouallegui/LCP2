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

import com.RNE.referentiel.dto.VilleDTO;
import com.RNE.referentiel.services.VilleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/referentiel/villes")
@AllArgsConstructor
public class VilleController {

	private final VilleService villeService;

	@PostMapping
	@PreAuthorize("hasRole('client_admin')")
	public ResponseEntity<VilleDTO> saveCity(@RequestBody VilleDTO villeDTO) {
		return new ResponseEntity<VilleDTO>(villeService.saveVille(villeDTO), HttpStatus.CREATED);
	}

	@GetMapping
	@PreAuthorize("hasRole('client_user')")
	public ResponseEntity<List<VilleDTO>> getAllCity() {
		return new ResponseEntity<List<VilleDTO>>(villeService.getAllVille(), HttpStatus.OK);
	}

	// get city by code
	@GetMapping("/{code}")
	public ResponseEntity<VilleDTO> getCityByCode(@PathVariable String code) {
		return new ResponseEntity<VilleDTO>(villeService.getVilleByCode(code), HttpStatus.OK);
	}

	// update city
	@PutMapping("/update/{code}")
	public ResponseEntity<VilleDTO> updateCity(@PathVariable String code, @RequestBody VilleDTO villeDTO) {
		return new ResponseEntity<VilleDTO>(villeService.updateVille(code, villeDTO), HttpStatus.OK);
	}

	// getActivate city
	@GetMapping("/Activated")
	public ResponseEntity<List<VilleDTO>> getActivatedCity() {
		return new ResponseEntity<List<VilleDTO>>(villeService.getActivatedVille(), HttpStatus.OK);
	}

	@DeleteMapping("/{code}")
	public ResponseEntity<String> deleteCity(@PathVariable String code) {
		villeService.deleteVille(code);
		return new ResponseEntity<String>("Ville successfully deleted!", HttpStatus.OK);
	}

}