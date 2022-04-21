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
package qrcodegen.modules;

import org.junit.*;
import qrcodegen.kml.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Arrays;
import java.util.Enumeration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 *
 * @author Stefan Ganzer
 */
public class GeoTreeMakerTest {

	public GeoTreeMakerTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void shouldCreateFolderForDocument() {
		Kml kml = new Kml();
		Document d = new Document();
		Folder f = new Folder();
		Placemark pm = new Placemark();
		Point p = new Point();

		p.setCoordinates("1,2,3");
		pm.setPoint(p);
		pm.setName("Statue of Liberty");
		f.setName("Sightseeing");
		f.setPlacemarks(Arrays.asList(pm));
		d.setFolders(Arrays.asList(f));
		kml.setDocument(d);

		GeoTreeMaker maker = new GeoTreeMaker(kml);
		maker.generateTree();
		DefaultMutableTreeNode node = maker.getTree();
		Enumeration<TreeNode> e = node.breadthFirstEnumeration();

		DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), kml);

		n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), d);

		n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), f);

		n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), pm);

		assertFalse(e.hasMoreElements());
	}
	@Test
	public void shouldNotCreateFolderForDocument(){
		Kml kml = new Kml();
		Document d = new Document();
		Folder f = new Folder();
		Placemark pm = new Placemark();
		Point p = new Point();
		
		p.setCoordinates("1,2,3");
		pm.setPoint(p);
		pm.setName("Statue of Liberty");
		f.setName("Sightseeing");
		f.setPlacemarks(Arrays.asList(pm));
		d.setFolders(Arrays.asList(f));
		kml.setDocument(d);
		
		GeoTreeMaker maker = new GeoTreeMaker(kml);
		maker.generateTree(false);
		DefaultMutableTreeNode node = maker.getTree();
		Enumeration<TreeNode> e = node.breadthFirstEnumeration();
		
		DefaultMutableTreeNode n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), kml);
		
		n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), f);
		
		n = (DefaultMutableTreeNode) e.nextElement();
		assertEquals(n.getUserObject(), pm);
		
		assertFalse(e.hasMoreElements());
		
	}
}