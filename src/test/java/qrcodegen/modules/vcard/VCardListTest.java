/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qrcodegen.modules.vcard;


import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 * @author Stefan Ganzer
 */
public class VCardListTest {
	
	private VCardList<VCardValue> list;
	
	public VCardListTest() {
	}
	
	@BeforeAll
	public static void setUpClass() {
	}
	
	@AfterAll
	public static void tearDownClass() {
	}
	
	@BeforeEach
	public void setUp() {
		list = new VCardComponentList<VCardValue>(new ArrayList<VCardValue>());
	}
	
	@AfterEach
	public void tearDown() {
	}

	/**
	 * Test of toString method, of class VCardArrayList.
	 */
	@Test
	public void testGetValueAsString() {
		System.out.println("getValueAsString");
		
		list.add(new VCardText("Mustermann"));
		list.add(new VCardText("Erika"));
		
		String expResult = "Mustermann;Erika";
		String result = list.getValueAsString();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetValueAsStringEmptyList() {
		System.out.println("getValueAsStringEmptyList");
			
		String expResult = "";
		String result = list.getValueAsString();
		assertEquals(expResult, result);
	}
	
	@Test
	public void testNestedListGetValueAsString() {
		System.out.println("nestedListGetValueAsString");
		
		VCardList<VCardText> valueList = VCardValueList.newArrayList();
		valueList.add(new VCardText("Dr.med."));
		valueList.add(new VCardText("Dr.med.dent."));
		
		list.add(new VCardText("Mustermann"));
		list.add(new VCardText("Erika"));
		list.add(valueList);
		
		String expResult = "Mustermann;Erika;Dr.med.,Dr.med.dent.";
		String result = list.getValueAsString();
		assertEquals(expResult, result);
	}
}
