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
package qrcodegen.swing;

import org.junit.jupiter.api.*;

import javax.swing.filechooser.FileFilter;
import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 * @author Stefan Ganzer
 */
public class ExtendedJFileChooserTest {
	
	public ExtendedJFileChooserTest() {
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
	public void getFileNameExtensionFilterShouldReturnNullIfIsFileFilter(){
		ExtendedJFileChooser chooser = new ExtendedJFileChooser();
		chooser.setFileFilter(new MockFileFilter());
		FileFilter result = chooser.getFileExtensionFilter();
		assertThat(result, nullValue());
	}
	
	@Test
	public void getFileNameExtensionFilterShouldReturnSameIfIsFileNameExtensionFilter(){
		FileFilter filter = new FileExtensionFilter("Text", "txt");
		ExtendedJFileChooser chooser = new ExtendedJFileChooser();
		chooser.setFileFilter(filter);
		FileFilter result = chooser.getFileExtensionFilter();
		assertThat(result, sameInstance(filter));
	}

	@Test
	public void getFileNameExtensionFilterShouldReturnNullForDefaultFilter(){
		ExtendedJFileChooser chooser = new ExtendedJFileChooser();
		FileFilter result = chooser.getFileExtensionFilter();
		assertThat(result, nullValue());
	}
	
	@Test
	public void hasFileNameExtensionFilterShouldReturnFalseForDefaultFilter(){
		ExtendedJFileChooser chooser = new ExtendedJFileChooser();
		boolean result = chooser.currentFilterIsFileExtensionFilter();
		assertThat(result, is(Boolean.FALSE));
	}
	
	@Test
	public void hasFileNameExtensionFilterShouldReturnTrueIfIsFileNameExtensionFilter(){
		FileFilter filter = new FileExtensionFilter("Text", "txt");
		ExtendedJFileChooser chooser = new ExtendedJFileChooser();
		chooser.setFileFilter(filter);
		boolean result = chooser.currentFilterIsFileExtensionFilter();
		assertThat(result, is(Boolean.TRUE));
	}
	
	private static class MockFileFilter extends FileFilter{

		@Override
		public boolean accept(File f) {
			return true;
		}

		@Override
		public String getDescription() {
			return "";
		}
		
	}

}
