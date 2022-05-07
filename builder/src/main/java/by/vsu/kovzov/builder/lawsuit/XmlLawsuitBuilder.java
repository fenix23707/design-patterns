package by.vsu.kovzov.builder.lawsuit;

import by.vsu.kovzov.models.Lawsuit;
import lombok.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

public class XmlLawsuitBuilder implements LawsuitBuilder {
    private String filePath;

    public XmlLawsuitBuilder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Lawsuit> getAllLawsuits() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Lawsuits.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Lawsuits lawsuits = (Lawsuits) unmarshaller.unmarshal(new File(filePath));
            return lawsuits.getLawsuits();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @XmlRootElement(name = "lawsuits")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    private static class Lawsuits {
        @XmlElement(name = "lawsuit")
        private List<Lawsuit> lawsuits;
    }

}
