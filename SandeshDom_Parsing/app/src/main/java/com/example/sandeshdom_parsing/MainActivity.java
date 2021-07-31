package com.example.sandeshdom_parsing;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    public static String url="https://timesofindia.indiatimes.com/rssfeedstopstories.cms";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db=dbf.newDocumentBuilder();

            Document d=db.parse(url);

            d.normalize();

            NodeList nodeList=d.getElementsByTagName("item");

            for (int i=0;i<nodeList.getLength();i++)
            {
                Node node=nodeList.item(i);
                Element element= (Element) node;
                String title=getElementByName(element,"title");
                Log.d("mydata",title);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getElementByName(Element element,String title)
    {
        NodeList nodeList=element.getElementsByTagName(title);
        Node node=nodeList.item(0);
        Element child= (Element) node;
        String data=child.getTextContent();
        return data;
    }
}
