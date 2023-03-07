package br.com.mpituba.osapi.util;

import java.io.IOException;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;


/**
 * @author mpituba
 * Adapted class from:
 * https://gist.github.com/thiagofa/8ea41a1d58aeb74ece0adfc17b9a47ef
 *
 *This class get a json from file, puts into a stream and then the info may be send to a variable.
 */
public class GetJsonOnFile {

	public static String filePathAndName(String jsonFileName) {
	    try {
	    	
	        InputStream stream = GetJsonOnFile.class.getResourceAsStream(jsonFileName);
	        return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
	        
	    } catch (IOException e) {
	    	
	        throw new RuntimeException(e);
	    }
	    
	} 
	
}
