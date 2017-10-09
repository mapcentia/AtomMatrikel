package com.mapcentia.matrikel;

import java.util.Date;
import java.util.UUID;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import com.mapcentia.matrikel.models.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import java.io.StringWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Created by mh on 6/22/17.
 */
public class Parser {

    public void build(ZipInputStream stream, Integer elavsKode) throws Exception {

        Database database = new Database();


        // create a new DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setNamespaceAware(false);
        factory.setValidating(false);
        factory.setFeature("http://xml.org/sax/features/namespaces", false);
        factory.setFeature("http://xml.org/sax/features/validation", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        XPathFactory xPathfactory = XPathFactory.newInstance();

        XPath xpath = xPathfactory.newXPath();

        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource is = new InputSource(stream);

        Document doc = builder.parse(is);

        XPathExpression expr = xpath.compile("/FeatureCollection/featureMember/node()");

        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        Deque<Jordstykke> jordstykker = new ArrayDeque<>();
        Deque<OptagetVej> optagetVeje = new ArrayDeque<>();
        Deque<Centroide> centroider = new ArrayDeque<>();
        Deque<Fredskov> fredskove = new ArrayDeque<>();
        Deque<Strandbeskyttelse> strandbeskyttelser = new ArrayDeque<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeName() == "matrikel:Jordstykke" && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList cNodes = nodes.item(i).getChildNodes();
                Element e = (Element) cNodes;
                Jordstykke jordstykke = new Jordstykke();
                jordstykke.uuid = nodes.item(i).getAttributes().getNamedItem("gml:id").getNodeValue();
                jordstykke.featureID = Double.parseDouble(e.getElementsByTagName("matrikel:featureID").item(0).getFirstChild().getNodeValue());
                jordstykke.featureCode = Integer.parseInt(e.getElementsByTagName("matrikel:featureCode").item(0).getFirstChild().getNodeValue());
                jordstykke.featureType = e.getElementsByTagName("matrikel:featureType").item(0).getFirstChild().getNodeValue();
                jordstykke.ejerlavsnavn = e.getElementsByTagName("matrikel:ejerlavsnavn").item(0).getFirstChild().getNodeValue();
                jordstykke.landsejerlavskode = Double.parseDouble(e.getElementsByTagName("matrikel:landsejerlavskode").item(0).getFirstChild().getNodeValue());
                jordstykke.matrikelnummer = e.getElementsByTagName("matrikel:matrikelnummer").item(0).getFirstChild().getNodeValue();
                jordstykke.kms_SagsID = Double.parseDouble(e.getElementsByTagName("matrikel:kms_SagsID").item(0).getFirstChild().getNodeValue());
                jordstykke.kms_Journalnummer = (e.getElementsByTagName("matrikel:kms_Journalnummer").item(0) != null) ? e.getElementsByTagName("matrikel:kms_Journalnummer").item(0).getFirstChild().getNodeValue() : null;
                jordstykke.skelforretningsSagsID = Double.parseDouble(e.getElementsByTagName("matrikel:skelforretningsSagsID").item(0).getFirstChild().getNodeValue());
                jordstykke.supmSagsID = Double.parseDouble(e.getElementsByTagName("matrikel:supmSagsID").item(0).getFirstChild().getNodeValue());
                jordstykke.kommunenavn = e.getElementsByTagName("matrikel:kommunenavn").item(0).getFirstChild().getNodeValue();
                jordstykke.kommunekode = Integer.parseInt(e.getElementsByTagName("matrikel:kommunekode").item(0).getFirstChild().getNodeValue());
                jordstykke.sognenavn = e.getElementsByTagName("matrikel:sognenavn").item(0).getFirstChild().getNodeValue();
                jordstykke.sognekode = Integer.parseInt(e.getElementsByTagName("matrikel:sognekode").item(0).getFirstChild().getNodeValue());
                jordstykke.regionsnavn = e.getElementsByTagName("matrikel:regionsnavn").item(0).getFirstChild().getNodeValue();
                jordstykke.regionskode = Integer.parseInt(e.getElementsByTagName("matrikel:regionskode").item(0).getFirstChild().getNodeValue());
                jordstykke.retskredsnavn = e.getElementsByTagName("matrikel:retskredsnavn").item(0).getFirstChild().getNodeValue();
                jordstykke.retskredskode = Integer.parseInt(e.getElementsByTagName("matrikel:retskredskode").item(0).getFirstChild().getNodeValue());
                jordstykke.moderjordstykke = Double.parseDouble(e.getElementsByTagName("matrikel:moderjordstykke").item(0).getFirstChild().getNodeValue());
                jordstykke.registreretAreal = Double.parseDouble(e.getElementsByTagName("matrikel:registreretAreal").item(0).getFirstChild().getNodeValue());
                jordstykke.arealBeregn = e.getElementsByTagName("matrikel:arealBeregn").item(0).getFirstChild().getNodeValue();
                jordstykke.vejAreal = Integer.parseInt(e.getElementsByTagName("matrikel:vejAreal").item(0).getFirstChild().getNodeValue());
                jordstykke.vejArealBeregn = e.getElementsByTagName("matrikel:vejArealBeregn").item(0).getFirstChild().getNodeValue();
                jordstykke.vandArealBeregn = e.getElementsByTagName("matrikel:vandArealBeregn").item(0).getFirstChild().getNodeValue();
                jordstykke.faelleslod = e.getElementsByTagName("matrikel:faelleslod").item(0).getFirstChild().getNodeValue();
                jordstykke.dq_index = 1.0;
                jordstykke.registreringsdato = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:registreringsdato").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                jordstykke.geometridato = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:geometridato").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                jordstykke.timeOfCreation = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:timeOfCreation").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                jordstykke.surfaceProperty = nodeToString(e.getElementsByTagName("gml:surfaceProperty").item(0).getChildNodes().item(1));
                if (e.getElementsByTagName("matrikel:arealType").item(0) != null) {
                    jordstykke.arealType = e.getElementsByTagName("matrikel:arealType").item(0).getFirstChild().getNodeValue();
                }
                if (e.getElementsByTagName("matrikel:harSamletFastEjendom").item(0) != null) {
                    Element sfeNode = (Element) e.getElementsByTagName("matrikel:harSamletFastEjendom").item(0);
                    jordstykke.esr_Ejendomsnummer = Double.parseDouble(sfeNode.getElementsByTagName("matrikel:esr_Ejendomsnummer").item(0).getFirstChild().getNodeValue());
                    jordstykke.sfe_Ejendomsnummer = Double.parseDouble(sfeNode.getElementsByTagName("matrikel:sfe_Ejendomsnummer").item(0).getFirstChild().getNodeValue());
                    jordstykke.sfe_SagsID = Double.parseDouble(sfeNode.getElementsByTagName("matrikel:sfe_SagsID").item(0).getFirstChild().getNodeValue());
                    jordstykke.sfe_Registreringsdato = java.sql.Timestamp.valueOf(sfeNode.getElementsByTagName("matrikel:sfe_Registreringsdato").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                    jordstykke.sfe_Journalnummer = (e.getElementsByTagName("matrikel:sfe_Journalnummer").item(0) != null) ? sfeNode.getElementsByTagName("matrikel:sfe_Journalnummer").item(0).getFirstChild().getNodeValue() : null;
                    if (sfeNode.getElementsByTagName("matrikel:sfe_Registrering").item(0) != null) {
                        jordstykke.sfe_Registrering = sfeNode.getElementsByTagName("matrikel:sfe_Registrering").item(0).getFirstChild().getNodeValue();
                    }
                    if (sfeNode.getElementsByTagName("matrikel:l_NoteringsType").item(0) != null) {
                        jordstykke.l_NoteringsType = sfeNode.getElementsByTagName("matrikel:l_NoteringsType").item(0).getFirstChild().getNodeValue();
                    }
                }
                jordstykker.add(jordstykke);
            }
            if (nodes.item(i).getNodeName() == "matrikel:OptagetVej" && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList cNodes = nodes.item(i).getChildNodes();
                Element e = (Element) cNodes;
                OptagetVej optagetVej = new OptagetVej();
                optagetVej.uuid = nodes.item(i).getAttributes().getNamedItem("gml:id").getNodeValue();
                optagetVej.featureID = Double.parseDouble(e.getElementsByTagName("matrikel:featureID").item(0).getFirstChild().getNodeValue());
                optagetVej.featureCode = Integer.parseInt(e.getElementsByTagName("matrikel:featureCode").item(0).getFirstChild().getNodeValue());
                optagetVej.featureType = e.getElementsByTagName("matrikel:featureType").item(0).getFirstChild().getNodeValue();
                optagetVej.ejerlavsnavn = e.getElementsByTagName("matrikel:ejerlavsnavn").item(0).getFirstChild().getNodeValue();
                optagetVej.landsejerlavskode = Double.parseDouble(e.getElementsByTagName("matrikel:landsejerlavskode").item(0).getFirstChild().getNodeValue());
                optagetVej.matrikelnummer = e.getElementsByTagName("matrikel:matrikelnummer").item(0).getFirstChild().getNodeValue();
                optagetVej.jordstykkeID = Integer.parseInt(e.getElementsByTagName("matrikel:jordstykkeID").item(0).getFirstChild().getNodeValue());
                optagetVej.kms_SagsID = Double.parseDouble(e.getElementsByTagName("matrikel:kms_SagsID").item(0).getFirstChild().getNodeValue());
                optagetVej.dq_ProdMetode = e.getElementsByTagName("matrikel:dq_ProdMetode").item(0).getFirstChild().getNodeValue();
                optagetVej.timeOfCreation = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:timeOfCreation").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                optagetVej.curveProperty = nodeToString(e.getElementsByTagName("gml:curveProperty").item(0).getChildNodes().item(1));
                optagetVeje.add(optagetVej);
            }
            if (nodes.item(i).getNodeName() == "matrikel:Centroide" && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList cNodes = nodes.item(i).getChildNodes();
                Element e = (Element) cNodes;
                Centroide centroide = new Centroide();
                centroide.uuid = nodes.item(i).getAttributes().getNamedItem("gml:id").getNodeValue();
                centroide.featureID = Double.parseDouble(e.getElementsByTagName("matrikel:featureID").item(0).getFirstChild().getNodeValue());
                centroide.featureCode = Integer.parseInt(e.getElementsByTagName("matrikel:featureCode").item(0).getFirstChild().getNodeValue());
                centroide.featureType = e.getElementsByTagName("matrikel:featureType").item(0).getFirstChild().getNodeValue();
                centroide.ejerlavsnavn = e.getElementsByTagName("matrikel:ejerlavsnavn").item(0).getFirstChild().getNodeValue();
                centroide.landsejerlavskode = Double.parseDouble(e.getElementsByTagName("matrikel:landsejerlavskode").item(0).getFirstChild().getNodeValue());
                centroide.matrikelnummer = e.getElementsByTagName("matrikel:matrikelnummer").item(0).getFirstChild().getNodeValue();
                centroide.jordstykkeID = Integer.parseInt(e.getElementsByTagName("matrikel:jordstykkeID").item(0).getFirstChild().getNodeValue());
                centroide.kms_SagsID = Double.parseDouble(e.getElementsByTagName("matrikel:kms_SagsID").item(0).getFirstChild().getNodeValue());
                centroide.kms_Journalnummer = (e.getElementsByTagName("matrikel:kms_Journalnummer").item(0) != null) ? e.getElementsByTagName("matrikel:kms_Journalnummer").item(0).getFirstChild().getNodeValue() : null;
                centroide.timeOfCreation = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:timeOfCreation").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                centroide.pointProperty = nodeToString(e.getElementsByTagName("gml:pointProperty").item(0).getChildNodes().item(1));
                centroider.add(centroide);
            }
            if (nodes.item(i).getNodeName() == "matrikel:Fredskov" && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList cNodes = nodes.item(i).getChildNodes();
                Element e = (Element) cNodes;
                Fredskov fredskov = new Fredskov();
                fredskov.uuid = nodes.item(i).getAttributes().getNamedItem("gml:id").getNodeValue();
                fredskov.featureID = Double.parseDouble(e.getElementsByTagName("matrikel:featureID").item(0).getFirstChild().getNodeValue());
                fredskov.featureCode = Integer.parseInt(e.getElementsByTagName("matrikel:featureCode").item(0).getFirstChild().getNodeValue());
                fredskov.featureType = e.getElementsByTagName("matrikel:featureType").item(0).getFirstChild().getNodeValue();
                fredskov.ejerlavsnavn = e.getElementsByTagName("matrikel:ejerlavsnavn").item(0).getFirstChild().getNodeValue();
                fredskov.landsejerlavskode = Double.parseDouble(e.getElementsByTagName("matrikel:landsejerlavskode").item(0).getFirstChild().getNodeValue());
                fredskov.matrikelnummer = e.getElementsByTagName("matrikel:matrikelnummer").item(0).getFirstChild().getNodeValue();
                fredskov.jordstykkeID = Integer.parseInt(e.getElementsByTagName("matrikel:jordstykkeID").item(0).getFirstChild().getNodeValue());
                fredskov.temaOmfang = e.getElementsByTagName("matrikel:temaOmfang").item(0).getFirstChild().getNodeValue();
                fredskov.areal = Integer.parseInt(e.getElementsByTagName("matrikel:areal").item(0).getFirstChild().getNodeValue());
                fredskov.timeOfCreation = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:timeOfCreation").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                if (e.getElementsByTagName("gml:surfaceProperty").item(0) != null) {
                    fredskov.surfaceProperty = nodeToString(e.getElementsByTagName("gml:surfaceProperty").item(0).getChildNodes().item(1));
                }
                if (e.getElementsByTagName("gml:multiSurfaceProperty").item(0) != null) {
                    fredskov.surfaceProperty = nodeToString(e.getElementsByTagName("gml:multiSurfaceProperty").item(0).getChildNodes().item(1));
                }
                fredskove.add(fredskov);
            }
            if (nodes.item(i).getNodeName() == "matrikel:Strandbeskyttelse" && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                NodeList cNodes = nodes.item(i).getChildNodes();
                Element e = (Element) cNodes;
                Strandbeskyttelse strandbeskyttelse = new Strandbeskyttelse();
                strandbeskyttelse.uuid = nodes.item(i).getAttributes().getNamedItem("gml:id").getNodeValue();
                strandbeskyttelse.featureID = Double.parseDouble(e.getElementsByTagName("matrikel:featureID").item(0).getFirstChild().getNodeValue());
                strandbeskyttelse.featureCode = Integer.parseInt(e.getElementsByTagName("matrikel:featureCode").item(0).getFirstChild().getNodeValue());
                strandbeskyttelse.featureType = e.getElementsByTagName("matrikel:featureType").item(0).getFirstChild().getNodeValue();
                strandbeskyttelse.ejerlavsnavn = e.getElementsByTagName("matrikel:ejerlavsnavn").item(0).getFirstChild().getNodeValue();
                strandbeskyttelse.landsejerlavskode = Double.parseDouble(e.getElementsByTagName("matrikel:landsejerlavskode").item(0).getFirstChild().getNodeValue());
                strandbeskyttelse.matrikelnummer = e.getElementsByTagName("matrikel:matrikelnummer").item(0).getFirstChild().getNodeValue();
                strandbeskyttelse.jordstykkeID = Integer.parseInt(e.getElementsByTagName("matrikel:jordstykkeID").item(0).getFirstChild().getNodeValue());
                strandbeskyttelse.temaOmfang = e.getElementsByTagName("matrikel:temaOmfang").item(0).getFirstChild().getNodeValue();
                strandbeskyttelse.areal = Integer.parseInt(e.getElementsByTagName("matrikel:areal").item(0).getFirstChild().getNodeValue());
                strandbeskyttelse.timeOfCreation = java.sql.Timestamp.valueOf(e.getElementsByTagName("matrikel:timeOfCreation").item(0).getFirstChild().getNodeValue().replace("T", " ").replace("Z", ""));
                if (e.getElementsByTagName("gml:surfaceProperty").item(0) != null) {
                    strandbeskyttelse.surfaceProperty = nodeToString(e.getElementsByTagName("gml:surfaceProperty").item(0).getChildNodes().item(1));
                }
                if (e.getElementsByTagName("gml:multiSurfaceProperty").item(0) != null) {
                    strandbeskyttelse.surfaceProperty = nodeToString(e.getElementsByTagName("gml:multiSurfaceProperty").item(0).getChildNodes().item(1));
                }
                strandbeskyttelser.add(strandbeskyttelse);
            }
        }
        database.insertJordstykker(jordstykker, elavsKode);
        database.insertOptagetVej(optagetVeje, elavsKode);
        database.insertCentroide(centroider, elavsKode);
        database.insertFredskov(fredskove, elavsKode);
        database.insertStrandbeskyttelse(strandbeskyttelser, elavsKode);
    }

    private String nodeToString(Node node) throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        // t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));

        return sw.toString();
    }
}
