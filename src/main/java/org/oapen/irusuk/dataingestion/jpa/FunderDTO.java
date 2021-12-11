package org.oapen.irusuk.dataingestion.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.oapen.irusuk.dataingestion.Funder;

@Entity(name = "Funder")
@Table(name = "funder")

public class FunderDTO extends Funder {
	
	@Id @Column(nullable=false)
	public String id;
	@Column(nullable=false)
	public String name;
	
	@ManyToMany(mappedBy = "funders")
	public Set<ItemDTO> items = new HashSet<>();

	@Override
	public String toString() {
		return "FunderDTO [id=" + id + ", name=" + name + "]";
	}

}
