/**
 * 
 */
package ms01288_COM1028_textprocessor_test;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import ms01288_COM1028_textprocessor.Window;

/**
 * @author marcstevens
 *
 */
public class WindowTest {

	@Test
	public void objectCreate() {
		Window window = new Window(100, 100, 600, 800);
		Rectangle rec = new Rectangle(100, 100, 600, 800);
		assertEquals(window.getBounds(), rec);
	}

}
