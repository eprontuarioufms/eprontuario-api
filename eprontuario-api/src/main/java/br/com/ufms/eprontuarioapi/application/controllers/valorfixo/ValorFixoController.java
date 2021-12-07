package br.com.ufms.eprontuarioapi.application.controllers.valorfixo;

import br.com.ufms.eprontuarioapi.domain.valorfixo.pojo.ValorFixoPojo;
import br.com.ufms.eprontuarioapi.domain.valorfixo.service.ValorFixoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/eprontuario-api/valor-fixo")
@Api(value = "Valor Fixo", tags = {"Valor Fixo"})
public class ValorFixoController {

    @Autowired
    private ValorFixoService valorFixoService;

    @GetMapping
    public Page<ValorFixoPojo> buscarTodosValoresFixosPage(@PageableDefault(sort = "id", direction = Direction.DESC) Pageable paginacao) {
        return valorFixoService.buscarTodos(paginacao);
    }

    @GetMapping(path = "/{idValorFixo}")
    public ResponseEntity<ValorFixoPojo> buscarValorFixoPorId(@PathVariable("idValorFixo") Long idValorFixo)  {
        return valorFixoService.buscarPorId(idValorFixo);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ValorFixoPojo> inserirValorFixo(@RequestBody ValorFixoPojo valorFixoPojo, UriComponentsBuilder uriComponentsBuilder)  {
        return valorFixoService.inserir(valorFixoPojo, uriComponentsBuilder);
    }

    @Transactional
    @PutMapping(path = "/{idValorFixo}")
    public ResponseEntity<ValorFixoPojo> atualizarValorFixo(@PathVariable("idValorFixo") Long idValorFixo, @RequestBody ValorFixoPojo valorFixoPojo)  {
        return valorFixoService.atualizar(idValorFixo, valorFixoPojo);
    }

    @Transactional
    @DeleteMapping(path = "/{idValorFixo}")
    public void deletarValorFixo(@PathVariable("idValorFixo") Long idValorFixo)  {
        valorFixoService.deletar(idValorFixo);
    }
}
