/*
 * Copyright (C) 2013 Stefan Ganzer
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
package qrcodegen.kml;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import java.util.zip.ZipException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Stefan Ganzer
 */
public class KMLFileReaderTest {

	private final Logger logger = Logger.getLogger(KMLFileReaderTest.class.getName());
	private final File path;

	public KMLFileReaderTest() throws URISyntaxException {
		path = new File(KMLFileReaderTest.class.getResource("testFiles").toURI());
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	public void readZippedFileShouldReadZippedKMLFile() throws ZipException, FileNotFoundException, IOException {
		File input = new File(path, "Google.kmz");
		KMLFileReader reader = new KMLFileReader(input, 1024 * 1024, logger);

		reader.readZippedFile();
		Kml kml = reader.getKml();
		assertNotNull(kml);
	}

	@Test()
	public void readZippedFileShouldThrowZipExceptionOnUncompressedFile() throws ZipException, FileNotFoundException, IOException {
		Assertions.assertThrows(ZipException.class, () -> {
			File input = new File(path, "doc.kml");
			KMLFileReader reader = new KMLFileReader(input, 1024 * 1024, logger);

			reader.readZippedFile();
		});
	}

	@Test
	public void readUncompressedFileShouldReadUncompressedKMLFile() throws FileNotFoundException, IOException {
		File input = new File(path, "doc.kml");
		KMLFileReader reader = new KMLFileReader(input, 1024 * 1024, logger);

		reader.readUncompressedFile();
		Kml kml = reader.getKml();
		assertNotNull(kml);
	}

	@Test()
	public void readUncompressedFileShouldThrowIOExceptionOnZippedFile() throws FileNotFoundException, IOException {
		Assertions.assertThrows(IOException.class, () -> {
		File input = new File(path, "Google.kmz");
		KMLFileReader reader = new KMLFileReader(input, 1024 * 1024, logger);

		reader.readUncompressedFile();
		});
	}

	@Test
	public void readShouldReadZippedKMLFile() throws ZipException, FileNotFoundException, IOException {
		File input = new File(path, "Google.kmz");
		KMLFileReader reader = new KMLFileReader(input, 1024 * 1024, logger);

		reader.readFile();
		Kml kml = reader.getKml();
		assertNotNull(kml);
	}

	@Test
	public void readShouldReadUncompressedKMLFile() throws ZipException, FileNotFoundException, IOException {
		File input = new File(path, "doc.kml");
		KMLFileReader reader = new KMLFileReader(input, 1024 * 1024, logger);

		reader.readFile();
		Kml kml = reader.getKml();
		assertNotNull(kml);
	}

	@Test()
	public void constructorShouldThrowNPEifFileIsNull() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new KMLFileReader(null, 0, logger);
		});
	}
}