package com.example.reusable.asset;

import java.io.IOException;
import java.io.StringReader;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class UnitTestFixture {
    
  
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



    public UnitTestFixture() {
    
        
    }

    public void setUp()  throws ParserConfigurationException, SAXException, IOException {
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

    public void tearDown() {
    }
    
  
 public void printDocument(Document doc)  {
      // Output the modified Node
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

    public Document getDoc1() {
        return doc1;
    }

    public Document getDoc2() {
        return doc2;
    }

    public Document getDoc3() {
        return doc3;
    }
}
