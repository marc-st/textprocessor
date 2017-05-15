/**
 * 
 */
package ms01288_COM1028_textprocessor_test;

import static org.junit.Assert.*;

import org.junit.Test;

import ms01288_COM1028_textprocessor.FileHandler;

/**
 * @author marcstevens
 *
 */
public class FileHandlerTest {
	@Test
	public void objectCreate() {
		FileHandler fh = new FileHandler("txt");
		assertEquals("txt", fh.getValidFileType());
	}
}
