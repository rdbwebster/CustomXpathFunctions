package com.example.reusable.asset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.xml.xpath.XPathFunctionException;
import oracle.fabric.common.xml.xpath.IXPathContext;
import oracle.fabric.common.xml.xpath.IXPathFunction;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** Mapper function that can be used by any SOA Suite component.
  *
 **/

    public class SortEmployees implements IXPathFunction {
      
        public Object call(IXPathContext context,  List args) {
     
                org.w3c.dom.Node employees = (Node) args.get(0);
                System.out.println("SortEmployees Retrieved employees ");
                try {
                    
                   sortNodes(employees, false);
                }
                catch (XPathFunctionException ex)
                {
                // Issue docs state call method throws XPathFunctionException but compiler/import disagrees
                //  http://download.oracle.com/docs/cd/E12839_01/apirefs.1111/e10695/oracle/fabric/common/xml/xpath/IXPathFunction.html
                    System.err.println("Error: SortEmployees XPath function exception, skipped processing: " + ex.getMessage());
                    employees = (Node) args.get(0);
                }   

                return employees;
            }
        
         /**
          * Sorts the nodes in a node list
          * 
          * @param nodeList - list whose nodes will be sorted
          * @param descending - true for sorting in descending order
          */
         public static Node sortNodes(Node inputNode, boolean descending) throws XPathFunctionException {

                 
                   List<Node> nodes = new ArrayList<Node>();
                   
                   if(inputNode==null) {
                       throw new XPathFunctionException("Input Node is null");
                   }
                   else {    
                       if(inputNode == null)
                           throw new XPathFunctionException("Expected input Node of type http://www.example.org : Employees " +
                                                             "received null document");
                       if(inputNode.getNamespaceURI() == null ||
                           !(inputNode.getNamespaceURI().equals("http://www.example.org")) ||
                           !inputNode.getLocalName().equals("Employees") )
                           throw new XPathFunctionException("Expected input Node of type http://www.example.org : Employees " +
                                                            "received " + inputNode.getNamespaceURI() + " : " + inputNode.getLocalName());
                       
                       NodeList employees = inputNode.getChildNodes();
                //       System.out.println("Found " + employees.getLength() + " Employees");
                  
                       if (employees.getLength() > 0) {
                          for (int i = 0; i < employees.getLength(); i++) {
                               Node tNode = employees.item(i);
                               nodes.add(tNode);
                          }
                          
                          Comparator comp = new EmpDeptComparator();
                          
                          if (descending)
                          {
                           //if descending is true, get the reverse ordered comparator
                               Collections.sort(nodes, Collections.reverseOrder(comp));
                          } else {
                               Collections.sort(nodes, comp);
                          }
           
                         for (Iterator iter = nodes.iterator(); iter.hasNext();) {
                               Node element = (Node) iter.next();
                               inputNode.appendChild(element);
                         }
                       }
                   }
           
               return inputNode;
              
           }
           }

           class EmpDeptComparator implements Comparator {

           public int compare(Object arg0, Object arg1) {
                   return ((Node) arg0).getFirstChild().getTextContent().compareTo(
                                   ((Node) arg1).getLastChild().getTextContent());
           }
        
       }