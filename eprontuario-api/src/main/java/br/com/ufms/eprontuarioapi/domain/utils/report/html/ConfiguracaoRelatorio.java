package br.com.ufms.eprontuarioapi.domain.utils.report.html;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class ConfiguracaoRelatorio {

    private static final String PREFIXO_RELATORIOS_HTML = "relatorios/html";
    private static final String PREFIXO_TEMPLATE_HEADER = "header";
    private static final String PREFIXO_TEMPLATE_FOOTER = "footer";
    private static final String SUFIXO_TEMPLATE = "template";
    private static final String NOME_TEMPLATE_HEADER_DEFAULT = "header-default";
    private static final String NOME_TEMPLATE_FOOTER_DEFAULT = "footer-default";

    private String nomeTemplateHeader;
    private String nomeTemplateFooter;
    private String nomeRelatorio;
    private Map<String, Object> parametros;

    public ConfiguracaoRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
        this.parametros = new HashMap<>();
    }

    public ConfiguracaoRelatorio(String nomeRelatorio, Map<String, Object> parametros) {
        this.nomeRelatorio = nomeRelatorio;
        this.parametros = parametros;
    }

    public ConfiguracaoRelatorio adicionarParametro(String chave, Object valor) {
        parametros.put(chave, valor);
        return this;
    }

    public ConfiguracaoRelatorio setNomeTemplateHeader(String nomeTemplateHeader) {
        this.nomeTemplateHeader = nomeTemplateHeader;
        return this;
    }

    public ConfiguracaoRelatorio setNomeTemplateFooter(String nomeTemplateFooter) {
        this.nomeTemplateFooter = nomeTemplateFooter;
        return this;
    }

    public String getTemplateBase() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, nomeRelatorio, SUFIXO_TEMPLATE);
    }

    public String getTemplateHeader() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_HEADER,
                Objects.isNull(nomeTemplateHeader) ? NOME_TEMPLATE_HEADER_DEFAULT : nomeTemplateHeader);
    }
    public String getTemplateFooter() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_FOOTER,
            Objects.isNull(nomeTemplateFooter) ? NOME_TEMPLATE_FOOTER_DEFAULT : nomeTemplateFooter);
    }

    public String getTemplateHeaderDefault() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_HEADER, NOME_TEMPLATE_HEADER_DEFAULT);
    }

    public String getTemplateFooterDefault() {
        return String.join(File.separator, PREFIXO_RELATORIOS_HTML, PREFIXO_TEMPLATE_HEADER, NOME_TEMPLATE_FOOTER_DEFAULT);
    }
}
