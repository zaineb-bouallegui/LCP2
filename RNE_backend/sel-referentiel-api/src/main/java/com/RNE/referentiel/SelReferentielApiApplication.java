package com.RNE.referentiel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.RNE")
public class SelReferentielApiApplication {


    public static void main(String[] args) {
        SpringApplication.run(SelReferentielApiApplication.class, args);
    }
    
 


}