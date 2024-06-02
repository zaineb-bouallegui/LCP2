package com.RNE.demande.services;

import java.util.List;
import com.RNE.demande.entities.Demande;

public interface DemandeService {

	public Demande saveDemande(Demande demande);

	public List<Demande> getAllDemandes();

	public Demande getDemandeById(Long id);

	public void deleteDemande(Long id);

	public Demande updateDemande(Long id, Demande demande);

}