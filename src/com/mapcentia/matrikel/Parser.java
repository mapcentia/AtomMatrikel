package com.mapcentia.matrikel;

import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import com.mapcentia.matrikel.models.Jordstykke;
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

    public void build(ZipInputStream stream) throws Exception {

        // create a new DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        XPathFactory xPathfactory = XPathFactory.newInstance();

        XPath xpath = xPathfactory.newXPath();

        DocumentBuilder builder = factory.newDocumentBuilder();

        InputSource is = new InputSource(stream);

        Document doc = builder.parse(is);

        XPathExpression expr = xpath.compile("/FeatureCollection/featureMember/node()");

        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

        Deque<Jordstykke> jordstykker = new ArrayDeque<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeName() == "matrikel:Jordstykke" && nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {

                NodeList cNodes = nodes.item(i).getChildNodes();

                Element e = (Element) cNodes;

                Jordstykke jordstykke = new Jordstykke();

                jordstykke.setFeatureId(e.getElementsByTagName("matrikel:featureID").item(0).getFirstChild().getNodeValue());
                jordstykke.setFeatureCode(Integer.parseInt(e.getElementsByTagName("matrikel:featureCode").item(0).getFirstChild().getNodeValue()));
                jordstykke.featureType = e.getElementsByTagName("matrikel:featureType").item(0).getFirstChild().getNodeValue();
                jordstykke.ejerlavsnavn = e.getElementsByTagName("matrikel:ejerlavsnavn").item(0).getFirstChild().getNodeValue();
                jordstykke.landsejerlavskode = e.getElementsByTagName("matrikel:landsejerlavskode").item(0).getFirstChild().getNodeValue();
                jordstykke.matrikelnummer = e.getElementsByTagName("matrikel:matrikelnummer").item(0).getFirstChild().getNodeValue();
                jordstykke.kms_SagsID = e.getElementsByTagName("matrikel:kms_SagsID").item(0).getFirstChild().getNodeValue();
                jordstykke.kms_Journalnummer = e.getElementsByTagName("matrikel:kms_Journalnummer").item(0).getFirstChild().getNodeValue();
                jordstykke.skelforretningsSagsID = e.getElementsByTagName("matrikel:skelforretningsSagsID").item(0).getFirstChild().getNodeValue();
                jordstykke.supmSagsID = e.getElementsByTagName("matrikel:supmSagsID").item(0).getFirstChild().getNodeValue();
                jordstykke.kommunenavn = e.getElementsByTagName("matrikel:kommunenavn").item(0).getFirstChild().getNodeValue();
                jordstykke.kommunekode = e.getElementsByTagName("matrikel:kommunekode").item(0).getFirstChild().getNodeValue();
                jordstykke.sognenavn = e.getElementsByTagName("matrikel:sognenavn").item(0).getFirstChild().getNodeValue();
                jordstykke.sognekode = e.getElementsByTagName("matrikel:sognekode").item(0).getFirstChild().getNodeValue();
                jordstykke.regionsnavn = e.getElementsByTagName("matrikel:regionsnavn").item(0).getFirstChild().getNodeValue();
                jordstykke.regionskode = e.getElementsByTagName("matrikel:regionskode").item(0).getFirstChild().getNodeValue();
                jordstykke.retskredsnavn = e.getElementsByTagName("matrikel:retskredsnavn").item(0).getFirstChild().getNodeValue();
                jordstykke.retskredskode = e.getElementsByTagName("matrikel:retskredskode").item(0).getFirstChild().getNodeValue();
                jordstykke.moderjordstykke = e.getElementsByTagName("matrikel:moderjordstykke").item(0).getFirstChild().getNodeValue();
                jordstykke.registreretAreal = e.getElementsByTagName("matrikel:registreretAreal").item(0).getFirstChild().getNodeValue();
                jordstykke.arealBeregn = e.getElementsByTagName("matrikel:arealBeregn").item(0).getFirstChild().getNodeValue();
                jordstykke.vejAreal = Integer.parseInt(e.getElementsByTagName("matrikel:vejAreal").item(0).getFirstChild().getNodeValue());
                jordstykke.vejArealBeregn = e.getElementsByTagName("matrikel:vejArealBeregn").item(0).getFirstChild().getNodeValue();
                jordstykke.vandArealBeregn = e.getElementsByTagName("matrikel:vandArealBeregn").item(0).getFirstChild().getNodeValue();
                jordstykke.faelleslod = e.getElementsByTagName("matrikel:faelleslod").item(0).getFirstChild().getNodeValue();
                jordstykke.registreringsdato = e.getElementsByTagName("matrikel:registreringsdato").item(0).getFirstChild().getNodeValue();
                jordstykke.geometridato = e.getElementsByTagName("matrikel:geometridato").item(0).getFirstChild().getNodeValue();
                jordstykke.surfaceProperty =nodeToString(e.getElementsByTagName("gml:surfaceProperty").item(0).getChildNodes().item(1));

                if (e.getElementsByTagName("matrikel:harSamletFastEjendom").item(0) != null) {

                    Element sfeNode = (Element) e.getElementsByTagName("matrikel:harSamletFastEjendom").item(0);

                    jordstykke.esr_Ejendomsnummer = sfeNode.getElementsByTagName("matrikel:esr_Ejendomsnummer").item(0).getFirstChild().getNodeValue();
                    jordstykke.sfe_Ejendomsnummer = sfeNode.getElementsByTagName("matrikel:sfe_Ejendomsnummer").item(0).getFirstChild().getNodeValue();
                    jordstykke.sfe_SagsID = sfeNode.getElementsByTagName("matrikel:sfe_SagsID").item(0).getFirstChild().getNodeValue();
                    jordstykke.sfe_Registreringsdato = sfeNode.getElementsByTagName("matrikel:sfe_Registreringsdato").item(0).getFirstChild().getNodeValue();
                    jordstykke.sfe_Journalnummer = sfeNode.getElementsByTagName("matrikel:sfe_Journalnummer").item(0).getFirstChild().getNodeValue();

                    if (sfeNode.getElementsByTagName("matrikel:sfe_Registrering").item(0) != null) {
                        jordstykke.sfe_Registrering = sfeNode.getElementsByTagName("matrikel:l_NoteringsType").item(0).getFirstChild().getNodeValue();
                    }

                    if (sfeNode.getElementsByTagName("matrikel:l_NoteringsType").item(0) != null) {
                        jordstykke.l_NoteringsType = sfeNode.getElementsByTagName("matrikel:l_NoteringsType").item(0).getFirstChild().getNodeValue();
                    }

                    //System.out.println("TEST " + jordstykke.feat_id.value);

                }


                jordstykker.add(jordstykke);
            }
        }

        Database database = new Database();

        database.insertJordstykker(jordstykker);
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
