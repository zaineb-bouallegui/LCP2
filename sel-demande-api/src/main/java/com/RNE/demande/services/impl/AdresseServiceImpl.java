package com.RNE.demande.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.RNE.demande.entities.Adresse;
import com.RNE.demande.repositories.AdresseRepository;
import com.RNE.demande.services.AdressService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AdresseServiceImpl implements AdressService {

	private AdresseRepository adresseRepository;

	@Override
	public Adresse saveAdress(Adresse adresse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adresse> getAllAdresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adresse getAdressById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adresse updateAdress(Long id, Adresse Adresse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAdress(Long id) {
		// TODO Auto-generated method stub
		
	}
	
}