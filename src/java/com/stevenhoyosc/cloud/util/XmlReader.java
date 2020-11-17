package com.stevenhoyosc.cloud.util;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author stevenhoyosc
 */
public class XmlReader {
    private String fileName;
    private File file;
    private Document doc;
    
    public XmlReader() {
        fileName = this.getClass().getClassLoader().getResource("dbconfig.xml").getPath();
    }

    public XmlReader(String file) {
        fileName = file;
    }
    
    public void setFileName(String f) {
        fileName = f;
    }

    public boolean initFile() {
        boolean rta;
        file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
            rta = true;
        } catch(ParserConfigurationException e) {
            rta = false;
            System.out.println(e);
        } catch(SAXException e) {
            rta = false;
            System.out.println(e);
        } catch(IOException e) {
            rta = false;
            System.out.println(e);
        }
        return rta;
    }

    public String parseFile(String field) {
        NodeList node, aux;
        Element element;
        String rta;
        
        doc.getDocumentElement().normalize();
        node = doc.getElementsByTagName(field);
        element = (Element)node.item(0);
        aux = element.getChildNodes();
        rta = aux.item(0).getNodeValue();

        return rta;
    }
    
}
