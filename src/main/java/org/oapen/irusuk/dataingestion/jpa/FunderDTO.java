package org.oapen.irusuk.dataingestion.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "Funder")
@Table(name = "funder")

public class FunderDTO {
	
	@Id @Column(nullable=false)
	public String id;
	@Column(nullable=false)
	public String name;
	
	@ManyToMany(mappedBy = "funders")
	public Set<ItemDTO> items = new HashSet<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunderDTO other = (FunderDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FunderDTO [id=" + id + ", name=" + name + "]";
	}	
	
	
	
}
