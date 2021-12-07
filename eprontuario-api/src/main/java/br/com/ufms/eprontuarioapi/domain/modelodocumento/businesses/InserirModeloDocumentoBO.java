package br.com.ufms.eprontuarioapi.domain.modelodocumento.businesses;

import br.com.ufms.eprontuarioapi.domain.documento.enumeration.EStatus;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.crud.ModeloDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.entity.ModeloDocumento;
import br.com.ufms.eprontuarioapi.domain.modelodocumento.pojo.ModeloDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.utils.enumerations.ESituacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class InserirModeloDocumentoBO {

    @Autowired
    private ModeloDocumentoCrudInterface modeloDocumentoCrudInterface;

    public ModeloDocumento executar(ModeloDocumentoPojo modeloDocumentoPojo)  {
        ModeloDocumento modeloDocumentoParaSalvar = modeloDocumentoPojo.gerarModeloDocumento();

        validarPreenchimentoDoModeloDocumento(modeloDocumentoParaSalvar);
        validarSeDocumentoJaFoiCadastradoComMesmoTitulo(modeloDocumentoParaSalvar);

        atribuirSituacaoDeDocumentoAtivo(modeloDocumentoParaSalvar);
        atribuirStatusDeNovoDocumento(modeloDocumentoParaSalvar);

        return modeloDocumentoCrudInterface.salvarEntidade(modeloDocumentoParaSalvar);
    }

    private void validarSeDocumentoJaFoiCadastradoComMesmoTitulo(ModeloDocumento modeloDocumento) {
        if (isExisteDocumentoComMesmoTitulo(modeloDocumento)){
            throw new IllegalArgumentException("Já existe um documento cadastrado com mesmo Titulo, cadastre um com titulo diferente ou altere o já existente.");
        }
    }

    private boolean isExisteDocumentoComMesmoTitulo(ModeloDocumento modeloDocumento) {
        return modeloDocumentoCrudInterface.isExisteDocumentoComMesmoTitulo(modeloDocumento.getTitulo());
    }

    private void validarPreenchimentoDoModeloDocumento(ModeloDocumento modeloDocumento) {
        if (isModeloNaoPossuiTitulo(modeloDocumento)){
            throw new IllegalArgumentException("Deve ser informado o Título do Documento.");
        }
    }

    private boolean isModeloNaoPossuiTitulo(ModeloDocumento modeloDocumento) {
        return isNull(modeloDocumento.getTitulo()) || modeloDocumento.getTitulo().isEmpty();
    }

    private void atribuirStatusDeNovoDocumento(ModeloDocumento modeloDocumentoParaSalvar) {
        modeloDocumentoParaSalvar.setStatus(EStatus.NOVO);
    }

    private void atribuirSituacaoDeDocumentoAtivo(ModeloDocumento modeloDocumentoParaSalvar) {
        modeloDocumentoParaSalvar.setSituacao(ESituacao.ATIVO);
    }

}
