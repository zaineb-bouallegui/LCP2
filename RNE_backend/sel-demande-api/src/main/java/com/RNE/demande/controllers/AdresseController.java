package com.RNE.demande.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RNE.demande.entities.Adresse;
import com.RNE.demande.services.AdressService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/demande/addresses")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AdresseController {

    private AdressService adressService;

    @PostMapping
    public ResponseEntity<Adresse> saveAddresse(@RequestBody Adresse adresse) {
        return new ResponseEntity<>(adressService.saveAdress(adresse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> getAllAdresses() {
        return new ResponseEntity<>(adressService.getAllAdresses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getAdressById(@PathVariable Long id) {
        return new ResponseEntity<>(adressService.getAdressById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> updateAddresse(@PathVariable Long id, @RequestBody Adresse adresse) {
        return new ResponseEntity<>(adressService.updateAdress(id, adresse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddresse(@PathVariable Long id) {
        adressService.deleteAdress(id);
        return new ResponseEntity<>("Adresse deleted successfully", HttpStatus.OK);
    }
}