package com.github.meloflavio.ias.util;

import com.github.meloflavio.ias.contracts.CertificadoFactory;
import com.github.meloflavio.ias.contracts.OrganizacaoFactory;
import com.github.meloflavio.ias.contracts.ProfissionalFactory;

import java.io.*;
import java.util.Properties;
public class ConfigurationProperties {

    private static File file ;
    public static String CERTIFICADO_FACTORY_ADDRESS;
    public static String PROFISSIONAL_FACTORY_ADDRESS;
    public static String ORGANIZACAO_FACTORY_ADDRESS;
    private final Properties properties;


    public ConfigurationProperties() {

        properties = new Properties();

        try {
            file = new File("src/main/resources/constants.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            properties.load(fileInputStream);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Could not find the properties file" + fileNotFoundException);
        }
        catch (Exception exception) {
            System.out.println("Could not load properties file" + exception.toString());
        }


        CERTIFICADO_FACTORY_ADDRESS = (String) properties.getProperty("CERTIFICADO_FACTORY_ADDRESS");
        PROFISSIONAL_FACTORY_ADDRESS = (String) properties.getProperty("PROFISSIONAL_FACTORY_ADDRESS");
        ORGANIZACAO_FACTORY_ADDRESS = (String) properties.getProperty("ORGANIZACAO_FACTORY_ADDRESS");
    }

    public static void saveProperties(Properties p) throws IOException
    {
        FileOutputStream fr = new FileOutputStream(file);
        p.store(fr, "Properties");
        fr.close();
        CERTIFICADO_FACTORY_ADDRESS = p.getProperty("CERTIFICADO_FACTORY_ADDRESS");
        PROFISSIONAL_FACTORY_ADDRESS = p.getProperty("PROFISSIONAL_FACTORY_ADDRESS");
        ORGANIZACAO_FACTORY_ADDRESS = p.getProperty("ORGANIZACAO_FACTORY_ADDRESS");
        System.out.println("After saving properties: " + p);
    }

    public Properties getProperties() {
        return properties;
    }

    public static String getFactoryAddress(Class classe) {
        if(classe == ProfissionalFactory.class){
            return PROFISSIONAL_FACTORY_ADDRESS;
        }else if(classe == OrganizacaoFactory.class){
            return ORGANIZACAO_FACTORY_ADDRESS;
        }else if(classe == CertificadoFactory.class){
            return  CERTIFICADO_FACTORY_ADDRESS;
        }
        return null;

    }

    public static String getFactoryName(Class classe) {
        if(classe == ProfissionalFactory.class){
            return "PROFISSIONAL_FACTORY_ADDRESS";
        }else if(classe == OrganizacaoFactory.class){
            return "ORGANIZACAO_FACTORY_ADDRESS";
        }else if(classe == CertificadoFactory.class){
            return  "CERTIFICADO_FACTORY_ADDRESS";
        }
        return null;

    }
}