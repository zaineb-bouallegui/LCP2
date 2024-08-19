package com.RNE.demande.services;

import java.util.List;
import com.RNE.demande.entities.Adresse;

public interface AdressService {

	public Adresse saveAdress(Adresse adresse);

	public List<Adresse> getAllAdresses();

	public Adresse getAdressById(Long id);

	public Adresse updateAdress(Long id, Adresse Adresse);

	public void deleteAdress(Long id);
}