package com.RNE.demande.services.impl;

import org.springframework.stereotype.Service;

import com.RNE.demande.entities.Activite;
import com.RNE.demande.repositories.ActiviteRepository;
import com.RNE.demande.services.ActiviteService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ActiviteServiceImpl implements ActiviteService{
	
	private ActiviteRepository activiteRepository;
	

	@Override
	public Activite saveActivite(Activite activite) {
		// TODO Auto-generated method stub
		return activiteRepository.save(activite);
	}

}
