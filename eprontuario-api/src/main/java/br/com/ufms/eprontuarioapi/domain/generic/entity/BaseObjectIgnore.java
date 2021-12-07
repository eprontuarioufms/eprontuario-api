package br.com.ufms.eprontuarioapi.domain.generic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class BaseObjectIgnore extends ObjetoBase {

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataCadastro;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataAlteracao;

    @JsonIgnore
    protected String usuarioCadastro;

    @JsonIgnore
    protected String usuarioAlteracao;

    @Override
    public Date getDataAlteracao() {

        return dataAlteracao;
    }

    @Override
    public void setDataAlteracao(Date dataAlteracao) {

        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public Date getDataCadastro() {

        return dataCadastro;
    }

    @Override
    public void setDataCadastro(Date dataCadastro) {

        this.dataCadastro = dataCadastro;
    }

    @Override
    public String getUsuarioAlteracao() {

        return usuarioAlteracao;
    }

    @Override
    public void setUsuarioAlteracao(String usuarioAlteracao) {

        this.usuarioAlteracao = usuarioAlteracao;
    }

    @Override
    public String getUsuarioCadastro() {

        return usuarioCadastro;
    }

    @Override
    public void setUsuarioCadastro(String usuarioCadastro) {

        this.usuarioCadastro = usuarioCadastro;
    }
}
