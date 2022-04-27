/*
 * Copyright (C) 2012 Stefan Ganzer
 *
 * This file is part of QRCodeGen.
 *
 * QRCodeGen is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QRCodeGen is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package qrcodegen.tools;


import org.junit.jupiter.api.*;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author Stefan Ganzer
 */
public class ImageFileWriterTest {

	ImageFileWriter writer;
	RenderedImage image;
	File output;

	public ImageFileWriterTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() throws IOException {
		image = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
		writer = new ImageFileWriter(image);
	}

	@AfterEach
	public void tearDown() {
		image = null;
		writer = null;
		output = null;
	}

	@Test
	public void shouldWriteToFileIfIsJPGFile() throws FileNotFoundException, IOException {
		String extension = ".jpg";
		output = File.createTempFile("imageFileWriterTest", extension, null);
		output.deleteOnExit();
		assertEquals(0L, output.length());

		writer.toFile(output);

		assertTrue(output.exists());
		assertNotEquals(0L, output.length());
	}

	@Test
	public void shouldWriteToFileIfIsGIFFile() throws FileNotFoundException, IOException {
		String extension = ".gif";
		output = File.createTempFile("imageFileWriterTest", extension, null);
		output.deleteOnExit();
		assertEquals(0L, output.length());

		writer.toFile(output);

		assertTrue(output.exists());
		assertNotEquals(0L, output.length());
	}

	@Test
	public void shouldWriteToFileIfIsPNGFile() throws FileNotFoundException, IOException {
		String extension = ".png";
		output = File.createTempFile("imageFileWriterTest", extension, null);
		output.deleteOnExit();
		assertEquals(0L, output.length());

		writer.toFile(output);

		assertTrue(output.exists());
		assertNotEquals(0L, output.length());
	}

	@Test
	public void shouldThrowNPEIfImageIsNull() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			writer = new ImageFileWriter(null);
		});
	}

	@Test
	public void shouldThrowNPEIfFileIsNull() throws FileNotFoundException, IOException {
		Assertions.assertThrows(NullPointerException.class, () -> {
			writer.toFile(null);
		});
	}

	@Test
	public void shouldThrowFNFExceptionIfFileIsWriteProtected() throws FileNotFoundException, IOException {
		String extension = ".png";
		output = File.createTempFile("imageFileWriterTest", extension, null);
		output.deleteOnExit();
		assertEquals(0L, output.length());

		try {
			boolean readOnly = output.setReadOnly();
			assertTrue(readOnly);

			writer.toFile(output);
		} catch (FileNotFoundException fnfe) {
			return;
		} finally {
			output.setWritable(true);
			assertEquals(0L, output.length());
		}
		fail();
	}

	@Test
	public void shouldThrowIOExceptionForEmptyExtension() throws FileNotFoundException, IOException {
		Assertions.assertThrows(IOException.class, () -> {
			String extension = "";
			output = File.createTempFile("imageFileWriterTest", extension, null);
			output.deleteOnExit();
			assertEquals(0L, output.length());

			try {
				writer.toFile(output);
			} catch (IOException ioe) {
				throw new IOException(ioe);
			} finally {
				assertTrue(output.exists());
				assertEquals(0L, output.length());
			}
		});
	}

	@Test
	public void shouldThrowIOExceptionForNonImageExtension() throws FileNotFoundException, IOException {
		Assertions.assertThrows(IOException.class, () -> {
			String extension = ".txt";
			output = File.createTempFile("imageFileWriterTest", extension, null);
			output.deleteOnExit();
			assertEquals(0L, output.length());

			try {
				writer.toFile(output);
			} catch (IOException ioe) {
				throw new IOException(ioe);
			} finally {
				assertTrue(output.exists());
				assertEquals(0L, output.length());
			}
		});
	}
}
