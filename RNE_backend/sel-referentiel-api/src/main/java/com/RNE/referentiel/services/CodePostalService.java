package com.RNE.referentiel.services;

import java.util.List;

import com.RNE.referentiel.dto.CodePostalDTO;

public interface CodePostalService {

	public CodePostalDTO saveCodePostal(CodePostalDTO codePostalDTO);

	public CodePostalDTO getCodePostalById(Long id);

	public List<CodePostalDTO> getAllCodePostal();

	public CodePostalDTO updateCodePostal(Long id, CodePostalDTO codePostalDTO);

	public void deleteCodePostal(Long id);
}