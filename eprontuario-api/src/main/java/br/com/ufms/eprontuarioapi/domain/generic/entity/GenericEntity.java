package br.com.ufms.eprontuarioapi.domain.generic.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericEntity<T extends Serializable> extends BaseObjectIgnore{
    private static final long serialVersionUID = 1L;

    public abstract T getId();

    public abstract void setId(T id);

    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date dataAlteracao;

    protected String usuarioCadastro;
    protected String usuarioAlteracao;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GenericEntity x = (GenericEntity) obj;

        return getId() != null ? getId().equals(x.getId()) : x.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
