package com.example.reusable.asset;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathFunctionException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;


/*
 * JUnit 4 Example
 * With JUnit 4 Test methods use @Test annotation and to no longer need to end with the word test 
 * and the Test Class no longer needs to extend TestCase
 */

public class SortEmployeesTestUsingFixture {
    
 
    UnitTestFixture fixture1 = new UnitTestFixture();

    public SortEmployeesTestUsingFixture() {
    }

    @Before
    public void setUp() throws Exception {
            fixture1.setUp();       
    }

    @After
    public void tearDown() throws Exception {
          fixture1.tearDown();
    }


    /**
     * Test with Wrong document type, expect exception
     * @see SortEmployees#sortNodes(org.w3c.dom.Node,boolean)
     */
    @Test
    public void testWrongInputDocumentType() {
        try {
            SortEmployees.sortNodes(fixture1.getDoc1().getFirstChild(), false);
            fail("sortNodes() accepted an invalid document type");
            fixture1.printDocument(fixture1.getDoc1());
        } catch (XPathFunctionException ex) {
           assertTrue(true);
        }
    }


    /**
     * Test Document with no Employees
     * @see SortEmployees#sortNodes(org.w3c.dom.Node,boolean)
     */
    @Test
    public void testNoEmployees() {
        try {
            SortEmployees.sortNodes(fixture1.getDoc2().getFirstChild(), false);
          assertEquals("Found wrong number of Employees after sort. ", 0,
                       fixture1.getDoc2().getFirstChild().getChildNodes().getLength());

        } catch (XPathFunctionException ex) {
            fail("sortEmployees() threw exception processing document with no employees");
            ex.printStackTrace(System.out);
            fixture1.printDocument(fixture1.getDoc2());

        }
    }


    /**
     * Test with Valid Document
     * @see SortEmployees#sortNodes(org.w3c.dom.Node,boolean)
     */
    @Test
    public void testValidDocument() {
        try {
            Node employees = fixture1.getDoc3().getFirstChild();
            SortEmployees.sortNodes(fixture1.getDoc3().getFirstChild(), false);

            // Total Employees
            assertEquals("Found wrong number of Employees after sort. ", 3,
                         employees.getChildNodes().getLength());

            //   System.out.println(employees.getFirstChild().getTextContent());
            //   System.out.println(employees.getFirstChild().getNextSibling().getTextContent());

            // Employee 1 Dept expecting ""
            assertEquals("Employees in wrong order after sort. ", "",
                         employees.getFirstChild().getLastChild().getTextContent());

            // Employee 24 Dept expecting 102
            assertEquals("Employees in wrong order after sort. ", "102",
                         employees.getFirstChild().getNextSibling().getLastChild().getTextContent());

            // Employee 3 Dept expecting 109
            assertEquals("Employees in wrong order after sort. ", "109",
                         employees.getFirstChild().getNextSibling().getNextSibling().getLastChild().getTextContent());


         fixture1.printDocument(fixture1.getDoc3());

        } catch (XPathFunctionException ex) {
            fail("sortEmployees() threw exception processing valid document");
            ex.printStackTrace(System.out);
            fixture1.printDocument(fixture1.getDoc3());
        }
     }
}
    
 
  

