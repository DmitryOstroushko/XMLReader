package main.java.com.lianne.xml.audit.authorization;

import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class AuditAuthorizationProcessing {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        AuditAuthorizationParserHandler saxp = new AuditAuthorizationParserHandler();

        InputStream targetStream = Files.newInputStream(Paths.get("data/audit.xml"));

        parser.parse(targetStream, saxp);
    }
}
