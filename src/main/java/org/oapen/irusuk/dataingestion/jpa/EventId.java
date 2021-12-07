package org.oapen.irusuk.dataingestion.jpa;

import java.io.Serializable;
import java.time.LocalDate;

public class EventId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String ip;
    public LocalDate date;
    public String itemId;

    // default constructor
    public EventId() {}

    public EventId(String itemId, String ip, LocalDate date) {
        this.itemId = itemId;
        this.ip = ip;
        this.date = date;
    }

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
		EventId other = (EventId) obj;
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

}