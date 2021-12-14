package org.oapen.irusuk.dataingestion.jpa;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.oapen.irusuk.dataingestion.ToPersistableEntitiesMapper;
import org.oapen.irusuk.entities.IpAddress;
import org.oapen.irusuk.entities.PerformanceInstance;
import org.oapen.irusuk.entities.ReportItem;
import org.oapen.irusuk.iplookup.IpLookupService;

/**
 * Maps a {@link org.oapen.irusuk.entities.ReportItem} to 
 * a (persistable) {@link org.oapen.irusuk.dataingestion.Item}
 * and a (persistable) List of {@link org.oapen.irusuk.dataingestion.Event}s
 * <br><br>
 * Takes an IpLookupService to enrich IRUSUK data with geolocation data for 
 * ip addresses. IRUS UK data does contain ip addresses along with originating 
 * country data for requests, but without precise geographical data 
 * (city, latitude/longitude) so these finer grained fields are provided by 
 * IpLookupService during the mapping process. 
 * 
 * @author acdhirr
 *
 */
public class ReportItemToDTOMapper implements ToPersistableEntitiesMapper<ItemDTO,EventDTO> {
	
	private final IpLookupService<IpLocationDTO> ipLookupService;
	private final ReportItem reportItem;
	private final String itemId;

	/**
	 * Constructor 
	 * 
	 * @param reportItem ReportItem to map for persistence
	 * @param ipLookupService IpLocation provider to enrich EventDTOs
	 */
	public ReportItemToDTOMapper(ReportItem reportItem, IpLookupService<IpLocationDTO> ipLookupService) {
		this.ipLookupService = ipLookupService;
		this.reportItem = reportItem; 
		this.itemId = extractIdFromUri(reportItem.getUri());
	}
	
	public ItemDTO item() {
		
		ItemDTO itemDTO = new ItemDTO(); 
		
		itemDTO.setId(cutOff(extractIdFromUri(reportItem.getUri()), 255));
		itemDTO.setDoi(cutOff(reportItem.getDoi(), 255));
		itemDTO.setAuthors(cutOff(reportItem.getAuthors(), 300));
		itemDTO.setGrantNumber(cutOff(reportItem.getGrantNumber(), 255));
		itemDTO.setGrantProgram(cutOff(reportItem.getGrantProgram(), 255));
		itemDTO.setIrusId(cutOff(reportItem.getItemId(), 10));
		itemDTO.setIsbn(cutOff(reportItem.getISBN(), 13));
		itemDTO.setPlatform(cutOff(reportItem.getPlatform(), 255));
		itemDTO.setPublisherId(cutOff(reportItem.getPublisherId(), 36));
		itemDTO.setPublisherName(cutOff(reportItem.getPublisher(), 255));
		itemDTO.setTitle(cutOff(reportItem.getItem(), 300));
		itemDTO.setType(cutOff(reportItem.getItemType(), 32));
		itemDTO.setYear(reportItem.getYearOfPublishing());
		
		reportItem.getFunders().forEach(f -> {
			itemDTO.addFunder(funderDTO(f));
		});
		
		return itemDTO;
	}
	
	public List<EventDTO> events() {
		
		return reportItem.getPerformanceInstances()
			.stream()
			.map(this::eventDTO)
			.collect(Collectors.toList());
	}
	 

	private EventDTO eventDTO(PerformanceInstance performanceInstance) {
		
		EventDTO eventDTO = new EventDTO();
		
		eventDTO.setItemId(itemId);
	
		if (null != performanceInstance.getCountry()) {
			eventDTO.setCountry( 
				cutOff(performanceInstance.getCountry().getName(),255));
			eventDTO.setCountryCode( 
				cutOff(performanceInstance.getCountry().getCode(),2));
		}	
		
		if (null != performanceInstance.getEventMonth())
			eventDTO.setDate(performanceInstance.getEventMonth().atDay(1));
		
		if (null != performanceInstance.getMetricTypeCounts())
			eventDTO.setRequests(performanceInstance.getMetricTypeCounts().getTotalItemRequests());
		
		if (null != performanceInstance.getClientIP()) {
			
			IpAddress clientIp = performanceInstance.getClientIP();

			eventDTO.setIp(cutOff(clientIp.getIp(), 40));
			
			IpLocationDTO iploc = ipLookupService.findByIp(clientIp);
			System.out.println("Location for " + clientIp + " is " + iploc);
			
			if (null != iploc) {
				eventDTO.setCity(cutOff(iploc.getCity(), 255));
				eventDTO.setLatitude(iploc.getLatitude());
				eventDTO.setLongitude(iploc.getLongitude());
			}	
		}	
		
		return eventDTO;
	}
	
	private FunderDTO funderDTO(org.oapen.irusuk.entities.Funder funder) {
		
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
