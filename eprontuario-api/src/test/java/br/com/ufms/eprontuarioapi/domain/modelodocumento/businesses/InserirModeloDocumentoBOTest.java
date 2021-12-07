package br.com.ufms.eprontuarioapi.domain.modelodocumento.businesses;


import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo.ModeloDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
import br.com.ufms.eprontuarioapi.infra.repository.modelodocumento.repositorio.ModeloDocumentoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class InserirModeloDocumentoBOTest {

    @InjectMocks
    private InserirModeloDocumentoBO inserirModeloDocumentoBO;

    @Mock
    private ModeloDocumentoRepository modeloDocumentoRepository;

    @Test
    public void executarTeste() throws IllegalAccessException {

        ModeloDocumentoPojo modeloDocumentoPojo = ModeloDocumentoPojo.builder()
                .id(1L)
                .titulo("Titulo do Documento.")
                .descricao("Descricao Documento")
                .situacao(ESituacao.ATIVO)
                .status(EStatus.EXCLUIDO)
                .build();

        when(modeloDocumentoRepository.isExisteDocumentoComMesmoTituloIgnoreCase(anyString())).thenReturn(Boolean.FALSE);

        inserirModeloDocumentoBO.executar(modeloDocumentoPojo);

        verify(modeloDocumentoRepository, times(1)).save(Mockito.any(ModeloDocumento.class));
    }
}