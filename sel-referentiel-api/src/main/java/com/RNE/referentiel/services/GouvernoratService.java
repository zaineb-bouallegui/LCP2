package com.RNE.referentiel.services;

import java.util.List;

import com.RNE.referentiel.dto.GouvernoratDTO;

public interface GouvernoratService {

	public GouvernoratDTO saveGouvernorat(GouvernoratDTO gouvernoratDTO);

	public List<GouvernoratDTO> getAllGouvernorats();

	public GouvernoratDTO getGouvernoratByCode(String code);

	public GouvernoratDTO updateGouvernorat(String code, GouvernoratDTO gouvernoratDTO);

	public List<GouvernoratDTO> getActivatedGouvernorats();

	public void deleteGouvernorat(String code);
}