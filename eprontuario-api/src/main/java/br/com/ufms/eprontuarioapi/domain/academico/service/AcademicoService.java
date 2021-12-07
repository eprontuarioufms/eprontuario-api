package br.com.ufms.eprontuarioapi.domain.academico.service;

import br.com.ufms.eprontuarioapi.domain.academico.crud.AcademicoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.academico.entity.Academico;
import br.com.ufms.eprontuarioapi.domain.academico.exception.DeletarAcademicoInexistenteException;
import br.com.ufms.eprontuarioapi.domain.academico.exception.DeletarAcademicoVinculadoAUmDocumentoException;
import br.com.ufms.eprontuarioapi.domain.academico.pojo.AcademicoPojo;
import br.com.ufms.eprontuarioapi.domain.documento.crud.DocumentoCrudInterface;
import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class AcademicoService  {

    @Autowired
    private AcademicoCrudInterface academicoCrudInterface;

    @Autowired
    private DocumentoCrudInterface documentoCrudInterface;

    public Page<AcademicoPojo> buscarTodos(Pageable paginacao) {
        Page<Academico> documentoPage = academicoCrudInterface.buscarTodasEntidades(paginacao);
        return documentoPage.map(AcademicoPojo::gerarAcademicoPojo);
    }

    public ResponseEntity<AcademicoPojo> buscarPorId(Long idAcademico)  {
        return academicoCrudInterface.buscarEntidadePorId(idAcademico)
                .map(AcademicoPojo::gerarAcademicoPojo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<AcademicoPojo> inserir(AcademicoPojo academicoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        Academico academico = academicoPojo.gerarAcademico();
        Academico academicoSalvo = academicoCrudInterface.salvarEntidade(academico);

        URI uri = uriComponentsBuilder.path("/academico/{id}").buildAndExpand(academicoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(AcademicoPojo.gerarAcademicoPojo(academicoSalvo));
    }

    public ResponseEntity<AcademicoPojo> atualizar(Long idAcademico, AcademicoPojo academicoPojo) {
        return null;
    }//todo implementar

    public void deletar(Long idAcademico) throws GenericRuntimeException {
        validarSeAcademicoExiste(idAcademico);
        validarDependenciaDoAcademico(idAcademico);

        academicoCrudInterface.deletarEntidadePorId(idAcademico);
    }

    private void validarDependenciaDoAcademico(Long idAcademico) {
        if (documentoCrudInterface.existeDocumentoComMesmoIdDeAcademico(idAcademico)){
            throw new DeletarAcademicoVinculadoAUmDocumentoException("Academico está vinculado a um documento.");
        }
    }

    private void validarSeAcademicoExiste(Long idAcademico) {
        if (!academicoCrudInterface.existeEntidadePorId(idAcademico)){
            throw new DeletarAcademicoInexistenteException("Academico não existe para Deletar.");
        }
    }
}
