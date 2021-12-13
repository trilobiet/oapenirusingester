package org.oapen.irusuk.dataingestion;

import java.io.File;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Find JSON files to ingest.
 * <br/>
 * Filenames must conform to the naming pattern "YYYY-MM.json" 
 * 
 * @author acdhirr
 *
 */
public class IngestableFileFinder {
	
	private final Path path;
	private final YearMonth first;
	private final YearMonth last;
	private final String fileNamePattern = 
			"^(19|20)[0-9]{2}-(0[1-9]|1[0-2])\\.json$";

	public static String getFileNameWithoutExtension(String f) {
		return f.replaceFirst("[.][^.]+$", "");
	}
	
	/**
	 * Constructs an IngestableFileFinder looking for files
	 * on given path, who's name conforms to a year/month 
	 * between [first] and [last] (boundaries included).
	 * 	 
	 * @param path  Path to look for files (non recursive)
	 * @param first First year/month to include 
	 * @param last	Last year/month to include
	 */
	public IngestableFileFinder(Path path, YearMonth first, YearMonth last) {

		this.path = path;
		this.first = first;
		this.last = last;
	}
	
	/**
	 * Constructs an IngestableFileFinder looking for files
	 * on given path, who's name conforms to a year/month 
	 * starting at [first] and all months available thereafter.
	 * 
	 * @param path	Path to look for files (non recursive)
	 * @param first	First year/month to include
	 */
	public IngestableFileFinder(Path path, YearMonth first) {
		this(path, first, YearMonth.of(9999,12));
	}
	
	/**
	 * @return List of ingestable files for this IngestableFileFinder
	 */
	public List<File> collect() {
		
		return Stream.of(path.toFile().listFiles())
			.filter(file -> !file.isDirectory())
			.filter(file -> file.getName().matches(fileNamePattern))
			.sorted((a,b)-> a.getName().compareToIgnoreCase(b.getName()))
			.filter(this::isFileNameWithinLimits)
			.collect(Collectors.toList());
	}
	
	private boolean isFileNameWithinLimits(File file) {
		
		// 2020-04.json -> 2020-04
		String fileNameNoExt = getFileNameWithoutExtension(file.getName());
		
		return YearMonth.parse(fileNameNoExt).plusMonths(1).isAfter(first)
			&& YearMonth.parse(fileNameNoExt).minusMonths(1).isBefore(last);		
	}
	
}
