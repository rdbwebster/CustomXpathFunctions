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
 * This simple example uses the local setup() and teardown() methods rather than using a TestFixture class
 */

public class SortEmployeesTest {
    
      Document doc1 = null;
      Document doc2 = null;
      Document doc3 = null;
      
      String xmlString1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
        "<foo></foo>";
      
      String xmlString2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
        "<ns1:Employees xmlns:ns1=\"http://www.example.org\">" +
        "</ns1:Employees>";
      
      String xmlString3 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + 
        "<ns1:Employees xmlns:ns1=\"http://www.example.org\">" +
         "<ns1:Emp>" +
          "<ns1:number>190</ns1:number>" +
          "<ns1:firstName>Bob</ns1:firstName>" +
          "<ns1:lastName>Jones</ns1:lastName>" +
          "<ns1:job>Accountant</ns1:job>" +
          "<ns1:hiredate></ns1:hiredate>" +
          "<ns1:dept>109</ns1:dept>" +
        "</ns1:Emp>" +
        "<ns1:Emp>" +
          "<ns1:number>100</ns1:number>" +
          "<ns1:firstName>Peter</ns1:firstName>" +
          "<ns1:lastName>Baines</ns1:lastName>" +
          "<ns1:job>President</ns1:job>" +
          "<ns1:hiredate></ns1:hiredate>" +
          "<ns1:dept>102</ns1:dept>" +
        "</ns1:Emp>" +
        "<ns1:Emp>" +
          "<ns1:number>120</ns1:number>" +
          "<ns1:firstName>James</ns1:firstName>" +
          "<ns1:lastName>Last</ns1:lastName>" +
          "<ns1:job>Service Advisor</ns1:job>" +
          "<ns1:hiredate></ns1:hiredate>" +
          "<ns1:dept></ns1:dept>" +
        "</ns1:Emp>" +
        "</ns1:Employees>";


    public SortEmployeesTest() {
    }


    @Before
    public void setUp() throws Exception {
        
            DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = null;
            
            builder = factory.newDocumentBuilder();
            
            // Create empty doc 
            InputSource iSource1 = new InputSource( new StringReader(xmlString1));
            doc1 = builder.parse(iSource1);
            
            // create no employees doc 
            InputSource iSource2 = new InputSource( new StringReader(xmlString2));
            doc2 = builder.parse(iSource2);
            
            // create full doc
            InputSource iSource3 = new InputSource( new StringReader(xmlString3));
            doc3 = builder.parse(iSource3);
              
    }

    @After
    public void tearDown() throws Exception {
    }


    /**
     * Test with Wrong document type, expect exception
     * @see SortEmployees#sortNodes(org.w3c.dom.Node,boolean)
     */
    @Test
    public void testWrongInputDocumentType() {
        try {
            SortEmployees.sortNodes(doc1.getFirstChild(), false);
            fail("sortNodes() accepted an invalid document type");
            printDocument(doc1);
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
            SortEmployees.sortNodes(doc2.getFirstChild(), false);
          assertEquals("Found wrong number of Employees after sort. ", 0,
                       doc2.getFirstChild().getChildNodes().getLength());

        } catch (XPathFunctionException ex) {
            fail("sortEmployees() threw exception processing document with no employees");
            ex.printStackTrace(System.out);
            printDocument(doc2);

        }
    }


    /**
     * Test with Valid Document
     * @see SortEmployees#sortNodes(org.w3c.dom.Node,boolean)
     */
    @Test
    public void testValidDocument() {
        try {
            Node employees = doc3.getFirstChild();
            SortEmployees.sortNodes(doc3.getFirstChild(), false);

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


         printDocument(doc3);

        } catch (XPathFunctionException ex) {
            fail("sortEmployees() threw exception processing valid document");
            ex.printStackTrace(System.out);
            printDocument(doc3);
        }
    }
    
  public void printDocument(Document doc)  {
       // Print the Node to Stdout
      try {
         javax.xml.transform.TransformerFactory tfactory = TransformerFactory.newInstance();
         javax.xml.transform.Transformer xform = tfactory.newTransformer();
         xform.setOutputProperty(OutputKeys.INDENT, "yes");
        //   xform.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
         
         javax.xml.transform.Source src= new DOMSource(doc);
         java.io.StringWriter writer = new StringWriter();
         Result result = new StreamResult(writer);   
         xform.transform(src, result);
         System.out.println(writer.toString());
      }
      catch (TransformerConfigurationException tc) {
         System.out.println("printDocument: Unable to ouput document");
         tc.printStackTrace (System.out);
      }
      
      catch (TransformerException te) {
        System.out.println("printDocument: Unable to ouput document");
        te.printStackTrace (System.out);
      }
       
   }
  
 

}
