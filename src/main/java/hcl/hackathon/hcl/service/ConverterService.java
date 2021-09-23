package hcl.hackathon.hcl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConverterService {

    public void convertObjectToXml(Object objectToConvert) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        log.info("Object of type " + objectToConvert.getClass() + " was converted into xml");
        log.info(xmlMapper.writeValueAsString(objectToConvert));
    }

    public void convertJsonToXml(String jsonInput) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object currentObject = objectMapper.readValue(jsonInput, Object.class);

        XmlMapper xmlMapper = new XmlMapper();
        log.info("Object of type " + currentObject.getClass() + " was converted into xml");
        log.info(xmlMapper.writeValueAsString(currentObject));
    }

}