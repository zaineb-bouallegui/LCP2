package com.RNE.demande.entities;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Table(name = "DemandeArticles")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DemandeArticle implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = -5131993329433591346L;

		@Id
		@Column(name = "code", nullable = false)
		private String code;
		
		@Column(name = "code_proposition", nullable = false)
		private String codeProposition;
		
		@Column(name = "user_id")
		private Long userId;
		

}
