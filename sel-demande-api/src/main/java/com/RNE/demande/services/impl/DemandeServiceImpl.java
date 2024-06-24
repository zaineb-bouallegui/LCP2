package com.RNE.demande.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.RNE.demande.entities.Demande;
import com.RNE.demande.repositories.DemandeRepository;
import com.RNE.demande.services.DemandeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    private DemandeRepository demandeRepository;

	@Override
	public Demande saveDemande(Demande demande) {
		// TODO Auto-generated method stub
		return demandeRepository.save(demande);
	}

	@Override
	public List<Demande> getAllDemandes() {
		// TODO Auto-generated method stub
		return demandeRepository.findAll();
	}

	@Override
	public Demande getDemandeById(Long id) {
		// TODO Auto-generated method stub
		return demandeRepository.findById(id).get();
	}


	@Override
	public void deleteDemande(Long id) {
		// TODO Auto-generated method stub
		demandeRepository.deleteById(id);
	}

	@Override
	public Demande updateDemande(Long id, Demande demande) {
		// TODO Auto-generated method stub
		return null;
	}
    
}