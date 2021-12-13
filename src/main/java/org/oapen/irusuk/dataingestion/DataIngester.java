package org.oapen.irusuk.dataingestion;

import java.io.File;
import java.util.List;

/**
 * This interface contains a single method
 * to read files, ingest them (in another system)
 * and return a list of successfully ingested files.
 *  
 * @author acdhirr
 *
 */
public interface DataIngester {
	
	/**
	 * Ingest files	 
	 * @param files Files to ingest
	 * @return List of successfully ingested Files
	 */
	List<File> ingest(List<File> files);

}
