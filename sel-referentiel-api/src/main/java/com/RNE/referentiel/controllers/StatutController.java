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

import com.RNE.referentiel.dto.StatutDTO;
import com.RNE.referentiel.services.StatutService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/referentiel/statut")
@AllArgsConstructor
public class StatutController {

    private StatutService statutService;

    @PostMapping
    public ResponseEntity<StatutDTO> saveStatus(@RequestBody StatutDTO statutDTO) {
        return new ResponseEntity<StatutDTO>(statutService.saveStatut(statutDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StatutDTO>> getAllStatuses() {
        return new ResponseEntity<List<StatutDTO>>(statutService.getAllStatuts(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<StatutDTO> getStatusByCode(@PathVariable String code) {
        return new ResponseEntity<StatutDTO>(statutService.getStatutByCode(code), HttpStatus.OK);

    }

    @PutMapping("/update/{code}")
    public ResponseEntity<StatutDTO> updateStatus(@PathVariable String code, @RequestBody StatutDTO statutDTO) {
        return new ResponseEntity<StatutDTO>(statutService.updateStatut(code, statutDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteStatusCode(@PathVariable String code) {
        statutService.deleteStatut(code);
        return new ResponseEntity<String>("Statut successfully deleted!", HttpStatus.OK);
    }
}