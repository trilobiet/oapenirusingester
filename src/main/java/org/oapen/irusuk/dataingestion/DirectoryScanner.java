package org.oapen.irusuk.dataingestion;

import java.io.File;
import java.nio.file.Path;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryScanner {
	
	private final Path path;
	private final YearMonth first;
	private final YearMonth last;
	private final String fileNamePattern = 
			"^(19|20)[0-9]{2}-(0[1-9]|1[0-2])\\.json$";

	public static String getFileNameWithoutExtension(String f) {
		return f.replaceFirst("[.][^.]+$", "");
	}
	
	public DirectoryScanner(Path path, YearMonth first, YearMonth last) {

		this.path = path;
		this.first = first;
		this.last = last;
	}
	
	public DirectoryScanner(Path path, YearMonth first) {
		this(path, first, YearMonth.of(9999,12));
	}
	
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
