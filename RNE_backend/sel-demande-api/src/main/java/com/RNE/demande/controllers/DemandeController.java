package com.RNE.demande.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.RNE.demande.entities.Demande;
import com.RNE.demande.services.DemandeService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/demande/demandes")
//@CrossOrigin("*")
@AllArgsConstructor
public class DemandeController {

	private DemandeService demandeService;

	@PostMapping
	public ResponseEntity<Demande> saveRequest(@RequestBody Demande demande) {
		return new ResponseEntity<Demande>(demandeService.saveDemande(demande), HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Demande>> getAllRequests() {
		return new ResponseEntity<List<Demande>>(demandeService.getAllDemandes(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Demande> getRequestById(@PathVariable Long id) {
		return new ResponseEntity<Demande>(demandeService.getDemandeById(id), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Demande> updateRequest(@PathVariable Long id, @RequestBody Demande demande) {
		return new ResponseEntity<Demande>(demandeService.updateDemande(id, demande), HttpStatus.OK);
	}


	@DeleteMapping("delete/{id}")

	public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
		demandeService.deleteDemande(id);
		return new ResponseEntity<String>("Demande deleted successfully", HttpStatus.OK);
	}

}