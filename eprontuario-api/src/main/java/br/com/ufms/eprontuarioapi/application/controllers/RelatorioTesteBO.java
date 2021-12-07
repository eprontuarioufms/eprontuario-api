package br.com.ufms.eprontuarioapi.application.controllers;

import br.com.ufms.eprontuarioapi.domain.utils.report.html.ConfiguracaoRelatorio;
import br.com.ufms.eprontuarioapi.domain.utils.report.html.RelatorioHTMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelatorioTesteBO {

    @Autowired
    private RelatorioHTMLUtils relatorioHTMLUtils;

    @GetMapping("/relatorioPdf")
    public void relatorio() {
        ConfiguracaoRelatorio configuracaoRelatorio = relatorioHTMLUtils.construirConfiguracaoPadrao("teste", "Titulo Relatorio Balan")
                .adicionarParametro("usuario", "Eduardo Balan");

        relatorioHTMLUtils.baixarArquivo(configuracaoRelatorio);
    }
}
