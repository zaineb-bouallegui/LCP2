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

import com.RNE.referentiel.dto.PropositionDTO;
import com.RNE.referentiel.services.PropositionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/referentiel/proposition")
public class PropositionController {

    private PropositionService propositionService;

    @PostMapping
    public ResponseEntity<PropositionDTO> createProposal(@RequestBody PropositionDTO propositionDTO) {

        PropositionDTO savedProposalDTO = propositionService.saveProposition(propositionDTO);
        return new ResponseEntity<>(savedProposalDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropositionDTO>> getAllProposals() {
        return new ResponseEntity<List<PropositionDTO>>(propositionService.getAllPropositions(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PropositionDTO> getProposalByCode(@PathVariable String code) {
        return new ResponseEntity<PropositionDTO>(propositionService.getPropositionByCode(code), HttpStatus.OK);

    }

    @PutMapping("/update/{code}")
    public ResponseEntity<PropositionDTO> updateProposal(@PathVariable String code,
            @RequestBody PropositionDTO propositionDTO) {
        return new ResponseEntity<PropositionDTO>(propositionService.updateProposition(code, propositionDTO),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteProposal(@PathVariable String code) {
        propositionService.deleteProposition(code);
        return new ResponseEntity<String>("Proposition successfully deleted!", HttpStatus.OK);
    }

}