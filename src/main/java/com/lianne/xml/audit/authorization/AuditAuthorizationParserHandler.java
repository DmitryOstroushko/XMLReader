package main.java.com.lianne.xml.audit.authorization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import main.java.com.lianne.properties.XMLAuthorizationAuditProperties;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AuditAuthorizationParserHandler extends DefaultHandler {

    ArrayList<AuditAuthorization> auditList;
    AuditAuthorization audit;
    private String thisElement;
    private int countHeadElement;
    XMLAuthorizationAuditProperties properties;


    public AuditAuthorizationParserHandler() throws IOException {
        auditList = new ArrayList<>();
        audit = new AuditAuthorization();
        thisElement = "";
        countHeadElement = 0;
        properties = XMLAuthorizationAuditProperties.getInstance();
    }

    public ArrayList<AuditAuthorization> getResult() {
        return auditList;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    private void printLog(String title) {
        System.out.println(title + ", id: " + audit.getId() + ", start time: " + audit.getStartTime() +
                ", end time: " + audit.getEndTime() + ", countHeadElement: " + countHeadElement);
    }
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        printLog("Before start element " + qName);

        thisElement = qName;
        System.out.println("Element: " + qName);

            if (qName.equals(properties.getGoalElement())) {
                countHeadElement++;
            }
            switch (countHeadElement) {
                case 1:
                    if (qName.equals(properties.getGoalElement())) {
                        audit.setId(atts.getValue("id"));
                    }
                    break;
                case 2:
                    if ((qName.equals("xmlmessage")) && atts.getValue("type").equals("incoming")) {
                        audit.setStartTime(atts.getValue("starttime"));
                    }
                    if ((qName.equals("xmlmessage")) && atts.getValue("type").equals("outgoing")) {
                        audit.setEndTime(atts.getValue("endtime"));
                    }
                    break;
            }
        printLog("After start element " + qName);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        printLog("Before end element " + qName);
        System.out.println("Element: " + qName);
        try {
            if (qName.equals(XMLAuthorizationAuditProperties.getInstance().getGoalElement())) {
                    countHeadElement--;
                if (countHeadElement == 0) {
                    auditList.add(new AuditAuthorization(audit));
                    audit.clear();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        thisElement = "";
        printLog("After end element " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if ((countHeadElement == 2) && (thisElement.equals("transactionStatusCode"))) {
            audit.setTransactionStatusCode(new String(Arrays.copyOfRange(ch, start, start + length)));
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop parse XML...");
            System.out.println(auditList.size());
            auditList.forEach(System.out::println);
    }
}
