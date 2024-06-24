package com.RNE.demande.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RNE.demande.entities.Activite;
import com.RNE.demande.services.ActiviteService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/activite/activites")
@AllArgsConstructor

public class ActiviteController {
	
	private ActiviteService activiteService;
	
	@PostMapping
    public ResponseEntity<Activite> saveAddresse(@RequestBody Activite activite) {
        return new ResponseEntity<>(activiteService.saveActivite(activite), HttpStatus.CREATED);
    }

}
