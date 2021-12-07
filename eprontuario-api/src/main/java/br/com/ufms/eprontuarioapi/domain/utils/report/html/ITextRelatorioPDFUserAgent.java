package br.com.ufms.eprontuarioapi.domain.utils.report.html;

import br.com.ufms.eprontuarioapi.domain.generic.exceptions.GenericRuntimeException;
import com.google.common.net.MediaType;
import org.apache.commons.io.FilenameUtils;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.pdf.ITextUserAgent;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ITextRelatorioPDFUserAgent extends ITextUserAgent {

    private static final String LOCAL_RECURSO_CSS = "relatorios/html/css/";
    private static final String LOCAL_RECURSO_IMAGEM = "relatorios/misc/";
    private static final List TIPOS_SUPORTADOS_IMAGEM = Arrays.asList("png", "jpg", "svg");

    private ITextRenderer defaultRenderer;
    private ResourceConfig.ClasspathResourceLoader classpathResourceLoader;

    ITextRelatorioPDFUserAgent(ITextRenderer defaultRenderer, ResourceConfig.ClasspathResourceLoader classpathResourceLoader) {
        super(new ITextOutputDevice(defaultRenderer.getDotsPerPoint()));
        this.defaultRenderer = defaultRenderer;
        this.classpathResourceLoader = classpathResourceLoader;
    }

    public void aplicar() {
        SharedContext sharedContext = defaultRenderer.getSharedContext();
        this.setSharedContext(sharedContext);
        sharedContext.setUserAgentCallback(this);
    }

    @Override
    protected InputStream openStream(String uri) {
        try {
            if(Objects.isNull(uri)) {
                return null;
            }
            return classpathResourceLoader.getResource(redirecionar(uri)).getInputStream();

        } catch (Exception e) {
            throw new GenericRuntimeException("Erro ao carregar Recurso para gerar PDF.");
        }
    }

    private static String redirecionar(String uri) {
        String nomeRecurso = FilenameUtils.getName(uri);
        String extensao = FilenameUtils.getExtension(nomeRecurso);

        if (MediaType.CSS_UTF_8.subtype().equals(extensao))
            return LOCAL_RECURSO_CSS + nomeRecurso;
        else if (TIPOS_SUPORTADOS_IMAGEM.contains(extensao))
            return LOCAL_RECURSO_IMAGEM + nomeRecurso;

        throw new GenericRuntimeException("Recurso do tipo " + extensao + " não suportado");
    }
}
