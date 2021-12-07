package br.com.ufms.eprontuarioapi.domain.utils.report.html;


import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import liquibase.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;

@Component
public class RelatorioHTMLUtils {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(RelatorioHTMLUtils.class);

    private static final String UTF_8 = "UTF-8";
    private static final SimpleDateFormat FORMATADOR_DATA_HORA_DOWNLOAD = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat FORMATADOR_HORA = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private ResourceConfig.ClasspathResourceLoader classpathResourceLoader;

    public void baixarArquivo(ConfiguracaoRelatorio configuracaoRelatorio) {
        long logTempoIniciar = logIniciar(configuracaoRelatorio);

        try {
            String html = processarTemplateHTML(configuracaoRelatorio);
            String xhtml = converterParaXHTML(html);
            byte[] pdfBytes = this.gerarPDFBytes(xhtml);

            httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
            httpServletResponse.setHeader("content-disposition", "attachment; filename=\""
                    + gerarNomeRelatorioDownload(configuracaoRelatorio) + ".pdf\"");

            httpServletResponse.getOutputStream().write(pdfBytes);
            httpServletResponse.flushBuffer();
        } catch (IOException e) {
            throw new GenericRuntimeException("Erro ao tentar baixar o relatório: " + configuracaoRelatorio.getNomeRelatorio());
        } finally {
            logFinalizar(configuracaoRelatorio, logTempoIniciar);
        }
    }

    public String imprimirBase64(ConfiguracaoRelatorio configuracaoRelatorio) {
        long logTempoIniciar = logIniciar(configuracaoRelatorio);

        String html = processarTemplateHTML(configuracaoRelatorio);
        String xhtml = converterParaXHTML(html);
        byte[] pdfBytes = this.gerarPDFBytes(xhtml);

        String printBase64Binary = DatatypeConverter.printBase64Binary(pdfBytes);

        logFinalizar(configuracaoRelatorio, logTempoIniciar);
        return printBase64Binary;
    }

    public Map<String, Object> imprimirBase64EmMap(ConfiguracaoRelatorio configuracaoRelatorio) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("relatorio", imprimirBase64(configuracaoRelatorio));
        return resp;
    }


    public ConfiguracaoRelatorio construirConfiguracaoPadrao(String nomeRelatorio, String tituloRelatorio) {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TITULO_RELATORIO", tituloRelatorio);

        this.setDataHoraGeracaoRelatorio(parametros);

        return new ConfiguracaoRelatorio(nomeRelatorio, parametros);
    }

    private void setDataHoraGeracaoRelatorio(Map<String, Object> params) {
        params.put("HORA_GERACAO_RELATORIO", FORMATADOR_HORA.format(new Date()));
        params.put("DATA_GERACAO_RELATORIO", FORMATADOR_DATA.format(new Date()));
    }

    public void carregadorImage(Map<String, Object> parametros, byte[] bytesImagem, String chaveParametro) {
        if (Objects.nonNull(bytesImagem)) {
            parametros.put(chaveParametro, Base64Utils.encodeToString(bytesImagem));
        } else {
            throw new GenericRuntimeException("Erro ao carregar '" + chaveParametro + "' p/ o relatório.");
        }
    }

    private String processarTemplateHTML(ConfiguracaoRelatorio configuracaoRelatorio) {
        return templateEngine.process(configuracaoRelatorio.getTemplateBase(), construirContextoThymeleaf(configuracaoRelatorio));
    }

    private static Context construirContextoThymeleaf(ConfiguracaoRelatorio configuracaoRelatorio) {
        final Context context = new Context();
        context.setVariables(configuracaoRelatorio.getParametros());
        context.setVariable("templateHeader", configuracaoRelatorio.getTemplateHeader());
        context.setVariable("templateFooter", configuracaoRelatorio.getTemplateFooter());
        context.setVariable("templateHeaderDefault", configuracaoRelatorio.getTemplateHeaderDefault());
        context.setVariable("templateFooterDefault", configuracaoRelatorio.getTemplateFooterDefault());

        return context;
    }

    private byte[] gerarPDFBytes(String xHtml) {
        try (ByteArrayOutputStream pdfWriter = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFontDirectory("relatorios/times", EMBEDDED);
            new ITextRelatorioPDFUserAgent(renderer, classpathResourceLoader).aplicar();
            String xmlNormalizado = replaceCaracteresInvalidos(xHtml);
            renderer.setDocumentFromString(xmlNormalizado);
            renderer.layout();
            renderer.createPDF(pdfWriter);

            return pdfWriter.toByteArray();
        } catch (Exception e) {
            throw new GenericRuntimeException("Erro ao gerar PDF.");
        }
    }

    private static String converterParaXHTML(String html) {
        try (
                ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ) {
            Tidy tidy = new Tidy();
            tidy.setInputEncoding(UTF_8);
            tidy.setOutputEncoding(UTF_8);
            tidy.setXHTML(true);
            tidy.parseDOM(inputStream, outputStream);
            return outputStream.toString(UTF_8);
        } catch (IOException e) {
            throw new GenericRuntimeException("Erro ao converter p/ XHTML.");
        }
    }

    private String gerarNomeRelatorioDownload(ConfiguracaoRelatorio configuracaoRelatorio) {
        return configuracaoRelatorio.getNomeRelatorio() + FORMATADOR_DATA_HORA_DOWNLOAD.format(new Date());
    }

    private long logIniciar(ConfiguracaoRelatorio configuracaoRelatorio) {
        long tempoInicial = System.currentTimeMillis();
        LOGGER.info("" +
                "Geração de relatório iniciada: " +
                "Sistema fly-source " +
                "ID -> '" + tempoInicial + "'. " +
                "Modelo do relatório -> '" + configuracaoRelatorio.getNomeRelatorio() + "'. ");

        return tempoInicial;
    }

    private static void logFinalizar(ConfiguracaoRelatorio configuracaoRelatorio, Long tempoInicial) {
        long tempoFinal = System.currentTimeMillis();
        LOGGER.info("" +
                "Geração de relatório finalizada: " +
                "Sistema fly-source " +
                "ID -> '" + tempoInicial + "'. " +
                "Modelo -> '" + configuracaoRelatorio.getNomeRelatorio() + "'. " +
                "Tempo gasto  " + ((tempoFinal - tempoInicial) / 1000) + "s.");
    }

    private static String replaceCaracteresInvalidos(String html) {
        return html.replaceAll("“", "\"")
                .replaceAll("”", "\"")
                .replaceAll("–", "-")
                .replaceAll("[\\000]+", "");
    }
}
