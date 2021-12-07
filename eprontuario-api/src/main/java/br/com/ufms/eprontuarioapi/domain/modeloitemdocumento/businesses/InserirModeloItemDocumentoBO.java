package br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.businesses;

import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.crud.ModeloItemDocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.pojo.ModeloItemDocumentoPojo;
import br.com.ufms.eprontuarioapi.domain.utils.validador.Validator;
import br.com.ufms.eprontuarioapi.domain.valorfixo.crud.ValorFixoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.valorfixo.entity.ValorFixo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isBoolean;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isDataHora;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isDouble;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isInteger;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isString;
import static br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils.isValorFixo;
import static java.util.Objects.nonNull;

@Service
public class InserirModeloItemDocumentoBO {
    @Autowired
    private ModeloItemDocumentoCrudInterface modeloItemDocumentoCrudInterface;

    @Autowired
    private ValorFixoCrudInterface valorFixoCrudInterface;

    public ModeloItemDocumento executar(ModeloItemDocumentoPojo modeloItemDocumentoPojo)  {
        ModeloItemDocumento modeloItemDocumento = modeloItemDocumentoPojo.gerarModeloItemDocumento();

        validarControleItemDocumento(modeloItemDocumento);

        return modeloItemDocumentoCrudInterface.salvarEntidade(modeloItemDocumento);
    }

    private void validarControleItemDocumento(ModeloItemDocumento modeloItemDocumento) {
        Predicate<ModeloItemDocumento> isTipoDoItemFoiInformado = criarPredicateParaValidarTipoDoControleItemDocumento();
        Predicate<ModeloItemDocumento> isTituloDocumentoFoiInformado = criarPredicateParaValidarTituloDocumento();

        Validator.of(modeloItemDocumento)
                .validate(isTituloDocumentoFoiInformado, "Deve ser informado o titulo do item documento.")
                .validate(isTipoDoItemFoiInformado, "Deve ser informado o tipo da informação a ser salvo nos elementos do Documento")
                .get();
    }

    private Predicate<ModeloItemDocumento> criarPredicateParaValidarTituloDocumento() {
        return c -> nonNull(c.getModeloDocumento().getTitulo());
    }

    private Predicate<ModeloItemDocumento> criarPredicateParaValidarTipoDoControleItemDocumento() {
        List<String> chavesJaCadastradas = valorFixoCrudInterface.buscarTodasEntidades()
                .stream().map(ValorFixo::getChave)
                .collect(Collectors.toList());

        Predicate<ModeloItemDocumento> isTipoDoItemFoiInformado = c -> isString(c)    ||
                                                                       isBoolean(c)   ||
                                                                       isDouble(c)    ||
                                                                       isInteger(c)   ||
                                                                       isDataHora(c)  ||
                                                                       isValorFixo(c) &&
                                                                       ischaveDeValorFixoJaFoiCadastrada(chavesJaCadastradas, c);
        return isTipoDoItemFoiInformado;
    }

    private boolean ischaveDeValorFixoJaFoiCadastrada(List<String> chavesJaCadastradas, ModeloItemDocumento c) {
        return nonNull(c.getValorFixoChave()) && chavesJaCadastradas.contains(c.getValorFixoChave());
    }

}
