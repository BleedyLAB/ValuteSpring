package com.Bleedy.sourse;


import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.TreeMap;

@Component
public class GetXML {
    public String getXMLString() {
        String xml2String = null;
        try{
            URL rssURL = new URL("http://www.cbr.ru/scripts/XML_daily.asp");
            URLConnection yc = rssURL.openConnection();
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = bufReader.readLine();

            while( line != null){
                sb.append(line).append("\n");
                line = bufReader.readLine();
            }
            bufReader.close();

            xml2String = sb.toString();

        }catch(Exception ignored){

        }
        return xml2String;
    }
    public Map<String,String> getXML() {
        Map<String,String> valute = new TreeMap<>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("http://www.cbr.ru/scripts/XML_daily.asp");

            NodeList nodeList = document.getChildNodes();
            for(int x=0,size= 34; x<size; x++) {
                valute.put(nodeList.item(0).getChildNodes().item(x).getChildNodes().item(3).getTextContent(),nodeList.item(0).getChildNodes().item(x).getChildNodes().item(4).getTextContent());
            }
        } catch (Exception ignored) { }
        return valute;
    }
}