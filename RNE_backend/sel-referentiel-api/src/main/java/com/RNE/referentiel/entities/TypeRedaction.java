package com.RNE.referentiel.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "TypeRedaction")
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeRedaction  implements Serializable{
	
	@Id
  private String id;
  
  @OneToMany(mappedBy = "typeRedaction")
  List<Article>articles;
  
  

}
