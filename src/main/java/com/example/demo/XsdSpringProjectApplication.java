package com.example.demo;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.SAXException;

@SpringBootApplication
public class XsdSpringProjectApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(XsdSpringProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	      System.out.println("houses.xml validates against houses.xsd? "+validateXMLSchema("C:/houses.xsd", "C:/houses.xml"));
	      
		
	}

    
    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        
        try {
        	String language = "http://www.w3.org/XML/XMLSchema/v1.1";
            SchemaFactory factory = 
                    SchemaFactory.newInstance(language);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

}
