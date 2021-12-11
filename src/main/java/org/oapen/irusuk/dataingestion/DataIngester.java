package org.oapen.irusuk.dataingestion;

import java.io.File;
import java.util.List;

public interface DataIngester {
	
	public List<File> ingest(List<File> files);

}
