package org.oapen.irusuk.harvester;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.net.ssl.HttpsURLConnection;

public final class Harvester {
	
	private final URL url;
	
	public Harvester(URL url) throws MalformedURLException {
	
		this.url = url;
	}
	
	public void downloadTo(String filePath) throws IOException  {
		
		try (InputStream is = getInputStream(url)) {
			Path path = Path.of(filePath);
			Files.createDirectories(path.getParent());
			Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	public InputStream getInputStream(URL url) throws IOException {
		
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setConnectTimeout(60_000);
		int responseCode = con.getResponseCode();
		if (responseCode == 200) return con.getInputStream();
		else throw(new IOException("response code " + responseCode));
	}
	

	@Override
	public String toString() {
		return "Harvester [url=" + url + "]";
	}	
	

}
