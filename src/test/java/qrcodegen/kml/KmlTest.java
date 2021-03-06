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

import jakarta.xml.bind.JAXB;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 * @author Stefan Ganzer
 */
public class KmlTest {

	private final File path;

	public KmlTest() throws URISyntaxException {
		path = new File(KmlTest.class.getResource("testFiles").toURI());
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

	/**
	 * Test of getDocuments method, of class Kml.
	 */
	@Test
	public void testGetDocument() throws URISyntaxException {

		File doc = new File(path, "doc.kml");

		Kml kml = JAXB.unmarshal(doc, Kml.class);
		String expectedName = "Google";
		String expectedAddress = "2350 Bayshore Parkway<br/>Mountain View, CA 94043, United States";
		String exptectedCoordinates = "-122.095264,37.424275,0";

		assertThat(kml.getDocument(), nullValue());
		assertThat(kml.getFolder(), nullValue());

		Placemark pm = kml.getPlacemark();
		assertThat(pm, notNullValue());

		String actualName = pm.getName();
		String actualAddress = pm.getAddress();

		assertThat(actualName, equalTo(expectedName));
		assertThat(actualAddress, equalTo(expectedAddress));

		Point p = pm.getPoint();
		assertThat(p, notNullValue());

		String actualCoordinates = p.getCoordinates();
		assertThat(actualCoordinates, equalTo(exptectedCoordinates));

	}

	@Test
	public void testWriteDocument() throws URISyntaxException, IOException {

		Kml k = new Kml();
		Document d = new Document();
		Placemark pm = new Placemark();
		Point p = new Point();
		p.setCoordinates("0,0,0");
		pm.setPoint(p);

		List<Folder> folders = new ArrayList<Folder>();
		Folder folder = new Folder();
		folder.setName("Main folder");
		folders.add(folder);
		d.setFolders(folders);

		List<Placemark> pms = new ArrayList<Placemark>();
		pms.add(pm);
		d.setPlacemarks(pms);
		k.setDocument(d);

		JAXB.marshal(k, System.out);
	}

	@Test
	public void testWriteKmlPlacemarkOnly() throws URISyntaxException, IOException {

		Kml k = new Kml();
		Placemark pm = new Placemark();
		Point p = new Point();
		p.setCoordinates("0,0,0");
		pm.setPoint(p);

		k.setPlacemark(pm);

		JAXB.marshal(k, System.out);
	}
	
		@Test
	public void testWriteKmlFolderOnly() throws URISyntaxException, IOException {

		Kml k = new Kml();
		Placemark pm = new Placemark();
		Point p = new Point();
		p.setCoordinates("0,0,0");
		pm.setPoint(p);

		Folder folder = new Folder();
		folder.setName("Main folder");
		folder.setPlacemarks(Arrays.asList(pm));

		k.setFolder(folder);

		JAXB.marshal(k, System.out);
	}

}