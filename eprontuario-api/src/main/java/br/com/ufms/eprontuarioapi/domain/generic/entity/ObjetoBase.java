package br.com.ufms.eprontuarioapi.domain.generic.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class ObjetoBase implements Serializable { //NOSONAR

    public abstract Date getDataCadastro();

    public abstract void setDataCadastro(Date dataCadastro);

    public abstract Date getDataAlteracao();

    public abstract void setDataAlteracao(Date dataAlteracao);

    public abstract String getUsuarioCadastro();

    public abstract void setUsuarioCadastro(String usuarioCadastro);

    public abstract String getUsuarioAlteracao();

    public abstract void setUsuarioAlteracao(String usuarioAlteracao);
}
