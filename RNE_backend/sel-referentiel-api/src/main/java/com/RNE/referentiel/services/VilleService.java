package com.RNE.referentiel.services;

import java.util.List;

import com.RNE.referentiel.dto.VilleDTO;

public interface VilleService {

	public VilleDTO saveVille(VilleDTO villeDTO);

	public VilleDTO getVilleByCode(String code);

	public List<VilleDTO> getAllVille();

	public VilleDTO updateVille(String code, VilleDTO villeDTO);

	public List<VilleDTO> getActivatedVille();

	public void deleteVille(String code);
}