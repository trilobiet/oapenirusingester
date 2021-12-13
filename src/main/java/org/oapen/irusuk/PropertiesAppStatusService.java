package org.oapen.irusuk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.YearMonth;
import java.util.Properties;

import org.oapen.irusuk.dataingestion.IRReportParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DefaultPropertiesPersister;

/**
 * AppStatus implementation that uses a local Properties file
 * to persist and read the last application status.
 * 
 * @author acdhirr
 *
 */
@Service
public class PropertiesAppStatusService implements AppStatus {
	
	private final String propertiesFileName; 
	private final String lastIngestionKey = "last_ingested_month";
	
	private static final Logger logger = 
		LoggerFactory.getLogger(IRReportParser.class);
	
	/**
	 * Constructs an PropertiesAppStatusService writing to and
	 * reading from local file. 
	 * @param propertiesFileName Local file path to store the status.
	 */
	public PropertiesAppStatusService(String propertiesFileName) {
		this.propertiesFileName = propertiesFileName;
	}

	@Override
	public YearMonth getLastIngestedMonth() {

		File f = new File(propertiesFileName);
		final YearMonth allTime = YearMonth.of(1900, 1);
		
		try (InputStream in = new FileInputStream(f)){
			Properties props = new Properties();
			props.load(in);
			if (props.containsKey(lastIngestionKey))
				return YearMonth.parse(props.getProperty(lastIngestionKey));
			else 
				return allTime;
		} catch (IOException e) {
			logger.warn(
				"No last ingested month found at " + f.getAbsolutePath() +
				" Maybe first run?"
			);
			return allTime;
		}
	}

	@Override
	public void setLastIngestedMonth(YearMonth ym) {

		File f = new File(propertiesFileName);
		File parent = f.getParentFile();
		if (!parent.exists()) parent.mkdirs();
		
		try (OutputStream out = new FileOutputStream(f)){
		    DefaultPropertiesPersister p = new DefaultPropertiesPersister();
		    Properties props = new Properties();
		    props.setProperty(lastIngestionKey, ym.toString());
		    p.store(props, out, "IRUS-UK report parser application state");
		} catch (IOException e) {
			logger.warn(
				"Could not store last ingested month at " +
				f.getAbsolutePath()
			);
		}
	}

}
