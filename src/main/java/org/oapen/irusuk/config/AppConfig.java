package org.oapen.irusuk.config;

import org.oapen.irusuk.AppStatus;
import org.oapen.irusuk.PropertiesAppStatusService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
public class AppConfig {
	
	@Value("${irusuk.baseurl}")
	private String baseUrl;
	@Value("${irusuk.requestorid}")
	private String requestorId;
	@Value("${irusuk.apikey}")
	private String apiKey;
	@Value("${irusuk.platform}")
	private String platform;
	
	@Bean(name="irusUkUrl")
	public String getIrusUkUrl() {
		
		return baseUrl 
			+ "?requestor_id=" + requestorId
			+ "&api_key=" + apiKey
			+ "&platform=" + platform
			+ "&attributes_to_show=Country|Client_IP"
			;
	}
	
	@Value("${app.path.app-status}")
	private String appStatusPath;
	
	@Bean
	public AppStatus appStatus() {
		return new PropertiesAppStatusService(appStatusPath);
	};
	
	/**
	 * https://stackoverflow.com/questions/7031905/automatically-trim-trailing-white-space-for-properties-in-props-file-loaded-into
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer createPropertyConfigurer() {
		PropertySourcesPlaceholderConfigurer propertyConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertyConfigurer.setTrimValues(true);
		return propertyConfigurer;
	}	
	
}
