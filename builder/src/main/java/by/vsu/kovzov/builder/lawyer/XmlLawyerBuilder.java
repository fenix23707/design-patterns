package by.vsu.kovzov.builder.lawyer;

import by.vsu.kovzov.models.Lawyer;
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

public class XmlLawyerBuilder implements LawyerBuilder {

    private String filePath;

    public XmlLawyerBuilder(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Lawyer> getAllLawyers() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Lawyers.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Lawyers lawyers = (Lawyers) unmarshaller.unmarshal(new File(filePath));
            return lawyers.getLawyers();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @XmlRootElement(name = "lawyers")
    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    private static class Lawyers {
        @XmlElement(name = "lawyer")
        private List<Lawyer> lawyers;
    }

}
