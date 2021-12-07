package org.oapen.irusuk.dataingestion;

import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.oapen.irusuk.dataingestion.jpa.EventDTO;
import org.oapen.irusuk.dataingestion.jpa.FunderDTO;
import org.oapen.irusuk.dataingestion.jpa.IpLocationDTO;
import org.oapen.irusuk.dataingestion.jpa.ItemDTO;
import org.oapen.irusuk.entities.Funder;
import org.oapen.irusuk.entities.IpAddress;
import org.oapen.irusuk.entities.PerformanceInstance;
import org.oapen.irusuk.entities.ReportItem;

public class DTOMapper {
	
	private final TreeMap<Long, IpLocationDTO> ipLocationMap;
	private final ReportItem reportItem;
	private final String itemId;

	public DTOMapper(ReportItem reportItem, TreeMap<Long, IpLocationDTO> ipLocationMap) {
		this.ipLocationMap = ipLocationMap;
		this.reportItem = reportItem;
		this.itemId = extractIdFromUri(reportItem.getUri());
	}

	public ItemDTO itemDTO() {
		
		ItemDTO itemDTO = new ItemDTO(); 
		
		itemDTO.id = cutOff(extractIdFromUri(reportItem.getUri()), 255);
		itemDTO.doi = cutOff(reportItem.getDoi(), 255);
		itemDTO.authors = cutOff(reportItem.getAuthors(), 300);
		itemDTO.grantNumber = cutOff(reportItem.getGrantNumber(), 255);
		itemDTO.grantProgram = cutOff(reportItem.getGrantProgram(), 255);
		itemDTO.irusId = cutOff(reportItem.getItemId(), 10);
		itemDTO.isbn = cutOff(reportItem.getISBN(), 13);
		itemDTO.platform = cutOff(reportItem.getPlatform(), 255);
		itemDTO.publisherId = cutOff(reportItem.getPublisherId(), 36);
		itemDTO.publisherName = cutOff(reportItem.getPublisher(), 255);
		itemDTO.title = cutOff(reportItem.getItem(), 300);
		itemDTO.type = cutOff(reportItem.getItemType(), 32);
		itemDTO.year = reportItem.getYearOfPublishing();
		
		reportItem.getFunders().forEach((Funder f) -> {
			itemDTO.addFunder(funderDTO(f));
		});
		
		return itemDTO;
	}
	
	
	public List<EventDTO> eventDTOs() {
		
		return reportItem.getPerformanceInstances()
			.stream()
			.map(this::eventDTO)
			.collect(Collectors.toList());
	}
	
	

	private EventDTO eventDTO(PerformanceInstance performanceInstance) {
		
		EventDTO eventDTO = new EventDTO();
		
		eventDTO.itemId = itemId;
	
		if (null != performanceInstance.getCountry()) {
			eventDTO.country = 
				cutOff(performanceInstance.getCountry().getName(),255);
			eventDTO.countryCode = 
				cutOff(performanceInstance.getCountry().getCode(),2);
		}	
		
		if (null != performanceInstance.getEventMonth())
			eventDTO.date = performanceInstance.getEventMonth().atDay(1);
		
		if (null != performanceInstance.getMetricTypeCounts())
			eventDTO.requests = performanceInstance.getMetricTypeCounts().getTotalItemRequests();
		
		if (null != performanceInstance.getClientIP()) {
			
			IpAddress clientIp = performanceInstance.getClientIP();

			eventDTO.ip = cutOff(clientIp.getIp(), 40);
			
			IpLocationDTO iploc = 
				ipLocationMap.floorEntry(clientIp.toIp4Number()).getValue();
			
			if (null != iploc) {
				eventDTO.city = cutOff(iploc.city, 255);
				eventDTO.latitude = iploc.latitude;
				eventDTO.longitude = iploc.longitude;
			}	
		}	
		
		return eventDTO;
	}
	
	
	private FunderDTO funderDTO(Funder funder) {
		
		FunderDTO funderDTO = new FunderDTO();

		funderDTO.id = funder.getId();
		funderDTO.name = cutOff(funder.getName(),255);
		
		return funderDTO;
	}
	

	private String extractIdFromUri(String uri) {
		
		final String regex = "handle\\/([^$]*)$";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(uri);
		
		if (matcher.find()) return matcher.group(1);
		else return "";
	}

	
	private String cutOff(String s, int size) {
		
		if (s != null && s.length() > size) 
			return s.substring(0,size);
		else 
			return s;
	}
	
	
}
