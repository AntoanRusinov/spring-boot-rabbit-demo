package hcl.hackathon.hcl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConverterService {

    public String convertObjectToXml(Object objectToConvert) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String convertedXml = xmlMapper.writeValueAsString(objectToConvert);
        log.info("Object of type " + objectToConvert.getClass() + " was converted into xml");
        log.info(convertedXml);
        return convertedXml;
    }

    public String convertJsonToXml(String jsonInput) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Object currentObject = objectMapper.readValue(jsonInput, Object.class);

        XmlMapper xmlMapper = new XmlMapper();
        String convertedXml = xmlMapper.writeValueAsString(currentObject);

        log.info("Object of type " + currentObject.getClass() + " was converted into xml");
        log.info(convertedXml);
        return convertedXml;
    }

}