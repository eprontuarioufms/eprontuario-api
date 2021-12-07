package br.com.ufms.eprontuarioapi;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = {"br.com.ufms.eprontuarioapi"})
@SpringBootTest
public class EprontuarioApiApplicationTest {

    @Test
    public void contextLoads() {

    }
}