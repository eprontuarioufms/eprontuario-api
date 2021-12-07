package br.com.ufms.eprontuarioapi.domain.documento.businesss;

import br.com.ufms.eprontuarioapi.domain.documento.entity.Documento;
import br.com.ufms.eprontuarioapi.domain.modeloitemdocumento.entity.ModeloItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.entity.ItemDocumento;
import br.com.ufms.eprontuarioapi.domain.itemdocumento.exception.ItemDocumentoNaoEhPermitidoException;
import br.com.ufms.eprontuarioapi.domain.utils.ETipoItemDocumentoUtils;
import br.com.ufms.eprontuarioapi.domain.utils.validador.Validator;
import br.com.ufms.eprontuarioapi.infra.repository.modeloitemdocumento.repositorio.ModeloItemDocumentoRepository;
import br.com.ufms.eprontuarioapi.infra.repository.valorfixo.repositorio.ValorFixoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VerificarItensDocumentosValidosBO {

    @Autowired
    private ModeloItemDocumentoRepository modeloItemDocumentoRepository;

    @Autowired
    private ValorFixoRepository valorFixoRepository;


    public void executar(Documento documento, List<ItemDocumento> itensDocumento) {

//        itensDocumento.forEach(itemDocumento -> {
//
//            itemDocumento.setDocumento(documento);
//
//            List<ModeloItemDocumento> modelosItemDocumento = modeloItemDocumentoRepository.buscarTodosControlesPorTituloDocumentETituloItemDocumento(documento.getTitulo(), itemDocumento.getTitulo());
//
//            validarSeExisteControleItensDocumentos(modelosItemDocumento);
//
//            Validator validador = Validator.of(itemDocumento);
//
//            modelosItemDocumento.forEach(modeloDocumento -> {
//                if (isItemNaoEhObrigatorio(modeloDocumento)) {
//                    return;
//                }
//
//                validarIsDataHora(itemDocumento, validador, modeloDocumento);
//                validarIsValorDouble(itemDocumento, validador, modeloDocumento);
//                validarIsInteger(itemDocumento, validador, modeloDocumento);
//                validarIsString(itemDocumento, validador, modeloDocumento);
//                validarQuantidadeDoMesmoItemDocumento(itensDocumento, itemDocumento, validador, modeloDocumento);
//                validarValorFixo(itemDocumento, validador, modeloDocumento);
//            });
//
//            validador.get();
//        });
    }

    private void validarSeExisteControleItensDocumentos(List<ModeloItemDocumento> modelosItemDocumento) {
        if (modelosItemDocumento.isEmpty()){
            Logger.getGlobal().log(Level.SEVERE, "É necessário um cadastro prévio do item documento em Modelos documentos.");
            throw new ItemDocumentoNaoEhPermitidoException("Item Documento não é permitido.");
        }
    }

    private boolean isItemNaoEhObrigatorio(ModeloItemDocumento controleItem) {
        return !controleItem.getIsItemDocumentoObrigatorio();
    }

    private void validarValorFixo(ItemDocumento itemDocumento, Validator validador, ModeloItemDocumento controleItem) {
        if (Objects.nonNull(controleItem.getValorFixoChave())) {
            validador.validate(Objects.nonNull(itemDocumento.getId()), new Exception("valor não informado"));
            boolean existePorChaveValor = valorFixoRepository.existsByChaveAndValor(itemDocumento.getValorFixo().getChave(), itemDocumento.getValorFixo().getValor());
            validador.validate(!existePorChaveValor, new Exception("Valor fixo não confere: " + controleItem.getTitulo()));
        }
    }

    private void validarQuantidadeDoMesmoItemDocumento(List<ItemDocumento> itensDocumento, ItemDocumento itemDocumento, Validator validador, ModeloItemDocumento controleItem) {
        if (Objects.nonNull(controleItem.getQtdMaximaRegistros())) {
            long quantidadeRegistrosDoMesmoTipo = itensDocumento.stream()
                    .filter(itemDocumentoLocal -> itemDocumentoLocal.getTitulo().equals(itemDocumento.getTitulo()))
                    .filter(itemDocumentoLocal -> itemDocumentoLocal.getDocumento().getTitulo().equals(itemDocumento.getDocumento().getTitulo()))
                    .count();
            validador.validate(quantidadeRegistrosDoMesmoTipo <= controleItem.getQtdMaximaRegistros(), new Exception("Quantidade máxima de registro foi atingida"));
        }
    }

    private void validarIsString(ItemDocumento itemDocumento, Validator validador, ModeloItemDocumento controleItem) {
        if (ETipoItemDocumentoUtils.isString(controleItem)) {
            validador.validate(Objects.nonNull(itemDocumento.getValorTexto()), new Exception("O valor Preenchido deve ser Texto."));
        }
    }

    private void validarIsInteger(ItemDocumento itemDocumento, Validator validador, ModeloItemDocumento controleItem) {
        if (ETipoItemDocumentoUtils.isInteger(controleItem)) {
            validador.validate(Objects.nonNull(itemDocumento.getValorInteiro()), new Exception("O valor Preenchido deve ser Inteiro"));
        }
    }

    private void validarIsValorDouble(ItemDocumento itemDocumento, Validator validador, ModeloItemDocumento controleItem) {
        if (ETipoItemDocumentoUtils.isDouble(controleItem)) {
            validador.validate(Objects.nonNull(itemDocumento.getValorFlutuante()), new Exception("O valor Preenchido deve ser Decimal"));
        }
    }

    private void validarIsDataHora(ItemDocumento itemDocumento, Validator validador, ModeloItemDocumento controleItem) {
        if (ETipoItemDocumentoUtils.isDataHora(controleItem)) {
            validador.validate(Objects.nonNull(itemDocumento.getValorData()), new Exception("O valor Preenchido de " + controleItem.getModeloDocumento().getTitulo() + " é obrigatorio"));
        }
    }
}
