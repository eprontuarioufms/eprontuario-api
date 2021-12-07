package br.com.ufms.eprontuarioapi.domain.valorfixo.service;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ValorFixoServiceTest.class)
@DatabaseSetup(value = ValorFixoServiceTest.DATASET, type = DatabaseOperation.INSERT)
class ValorFixoServiceTest {

    protected static final String DATASET = "classpath:datasets/valorfixo/valor-fixo-test.xml";

    @BeforeEach
    void setUp() {
    }

    @Test
    void buscarTodos() {
    }

    @Test
    void buscarPorId() {
    }

    @Test
    void inserir() {
    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }
}