package org.oapen.irusuk.dataingestion.jpa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity(name = "Event")
@Table(name = "event")
@IdClass(EventId.class)
public class EventDTO {
	
	/* Combined key of three fields
	 * See https://www.baeldung.com/jpa-composite-primary-keys */
	@Id @Column(nullable=false)
	public String ip;
	@Id @Column(nullable=false)
	public LocalDate date;
	@Id @Column(nullable=false)
	public String itemId;
	
	public String country, countryCode;
	public String city;
	public Double latitude, longitude;
	public Integer requests;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
		EventDTO other = (EventDTO) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		
		return "EventDTO [itemId=" + itemId 
				+ ", date=" + date + ", ip=" + ip + "]";
	}


    
}
