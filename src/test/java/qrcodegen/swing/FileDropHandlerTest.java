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
package qrcodegen.swing;


import org.junit.jupiter.api.*;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 *
 * @author Stefan Ganzer
 */
public class FileDropHandlerTest {

	private List<FileNameExtensionFilter> ff;

	public FileDropHandlerTest() {
	}

	@BeforeAll
	public static void setUpClass() {
	}

	@AfterAll
	public static void tearDownClass() {
	}

	@BeforeEach
	public void setUp() {
		ff = Arrays.asList(new FileNameExtensionFilter("Text", "txt"),
				new FileNameExtensionFilter("Document", "doc"));
	}

	@AfterEach
	public void tearDown() {
		ff = null;
	}

	@Test
	public void constructorShouldThrowExceptionIfListOfFileFilterContainsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			List<FileNameExtensionFilter> ff = Arrays.asList(new FileNameExtensionFilter("test", "txt"), null);
			new FileDropHandler(ff, Logger.getAnonymousLogger());
		});
	}

	@Test
	public void constructorShouldThrowExceptionIfFileFilterIsNull() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new FileDropHandler((FileFilter) null, Logger.getAnonymousLogger());
		});
	}

	@Test
	public void noArgConstructor() {
		new FileDropHandler(Logger.getAnonymousLogger());
	}
	
	@Test
	public void shouldRemoveUnwantedFiles(){
		List<File> files = Arrays.asList(new File("test.doc"), new File("test.jpg"), new File("test.gif"), new File("test.txt"));
		List<File> expectedResult = Arrays.asList(new File("test.doc"), new File("test.txt"));
		FileDropHandler handler = new FileDropHandler(ff, Logger.getAnonymousLogger());
		
		List<File> actualResult = handler.getFilesPassingTheFileFilters(files);
		
		assertThat(actualResult, equalTo(expectedResult));
	}
	
}