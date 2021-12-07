package org.oapen.irusuk.dataingestion.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity(name = "Item")
@Table(name = "item")

public class ItemDTO {

	@Id @Column(nullable=false)
	public String id; // like "20.500.12657/34945"
	public String title, authors, doi, isbn, type, platform;
	public String publisherName, publisherId, grantNumber, grantProgram, irusId;
	public Short year;
    
    @ManyToMany(
    	fetch = FetchType.EAGER, // Eager, because there are only a few.	
    	cascade = {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinTable(name = "item_funder",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "funder_id")
    )
	public Set<FunderDTO> funders = new HashSet<>();
 
    public void addFunder(FunderDTO funder) {
        funders.add(funder);
        funder.items.add(this);
    }


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
		ItemDTO other = (ItemDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemDTO [id=" + id + ", title=" + title + ", funders=" + funders + "]";
	}
	
}
