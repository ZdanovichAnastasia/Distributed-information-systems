package DataAccess;

import DataAccessModels.ISerialRepository;
import DataAccessModels.SerialDto;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SerialRepository implements ISerialRepository {
    private Path filePath;

    public SerialRepository (Path path){
        this.filePath = path;
    }
    private static ISerialRepository repository;
    public static ISerialRepository getInstance(Path path){
        if(repository == null) repository = new SerialRepository(path);
        return repository;
    }

    @Override
    public List<SerialDto> getSerials(){
        List<SerialDto> serialDtoList = new ArrayList<>();
        Document document = parseXML();
        NodeList serials = document.getElementsByTagName("serial");
        Element element = null;
        for(int i = 0; i < serials.getLength(); i++){
            element = (Element) serials.item(i);
            SerialDto serial = new SerialDto(element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue(),
                    Double.parseDouble(element.getElementsByTagName("rating").item(0).getFirstChild().getNodeValue()),
                    Integer.parseInt(element.getElementsByTagName("count_episodes").item(0).getFirstChild().getNodeValue()));
            serialDtoList.add(serial);
        }
        return serialDtoList;
    }
    @Override
    public List<String> getSerialsNames(){
        List<String> namesList = new ArrayList<>();
        Document document = parseXML();
        NodeList serials = document.getElementsByTagName("serial");
        Element element = null;
        for(int i = 0; i < serials.getLength(); i++){
            element = (Element) serials.item(i);
            namesList.add(element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
        }
        return namesList;
    }

    @Override
    public void editSerial(String name, SerialDto serialDto){
        try{
            Document document = parseXML();
            NodeList serials = document.getElementsByTagName("serial");
            Element element = null;
            for(int i = 0; i < serials.getLength(); i++){
                element = (Element) serials.item(i);
                if(element.getElementsByTagName("name").item(0).getFirstChild().getNodeValue().equals(name)){
                    Node nameS = element.getElementsByTagName("name").item(0).getFirstChild();
                    Node rating = element.getElementsByTagName("rating").item(0).getFirstChild();
                    Node count_episodes = element.getElementsByTagName("count_episodes").item(0).getFirstChild();
                    nameS.setNodeValue(serialDto.getName());
                    rating.setNodeValue(String.valueOf(serialDto.getRating()));
                    count_episodes.setNodeValue(String.valueOf(serialDto.getCount_episodes()));
                }
            }
            document.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath.toString()));
            transformer.transform(source, result);

        }catch (TransformerException e1) {
            e1.printStackTrace();
        }
    }
    private Document parseXML(){
        File xmlFile = new File(filePath.toString());
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document document = null;
        try{
            builder = builderFactory.newDocumentBuilder();
            document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
        }catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
        return document;
    }

}
