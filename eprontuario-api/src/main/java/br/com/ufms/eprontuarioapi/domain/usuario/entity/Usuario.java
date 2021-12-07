package br.com.ufms.eprontuarioapi.domain.usuario.entity;

import br.com.ufms.eprontuarioapi.domain.generic.entity.GenericEntity;
import br.com.ufms.eprontuarioapi.domain.perfil.entity.Perfil;
import br.com.ufms.eprontuarioapi.domain.pessoa.entity.Pessoa;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_USUARIO")
@SequenceGenerator(name = "seq_usuario", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "dataCadastro", column = @Column(name = "USU_DTHR_CADASTRO")),
        @AttributeOverride(name = "dataAlteracao", column = @Column(name = "USU_DTHR_ALTERACAO")),
        @AttributeOverride(name = "usuarioCadastro", column = @Column(name = "USU_USUARIO_CADASTRO")),
        @AttributeOverride(name = "usuarioAlteracao", column = @Column(name = "USU_USUARIO_ALTERACAO"))
})
public class Usuario extends GenericEntity<Long> implements UserDetails  {

    @Id
    @Column(name = "USU_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USU_NOME")
    private String nome;

    @OneToOne
    @JoinColumn(name="PES_ID")
    private Pessoa pessoa;

    @Column(name = "USU_LOGIN")
    private String login;

    @Column(name = "USU_EMAIL")
    private String email;

    @Column(name = "USU_SENHA")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PER_ID")
    private List<Perfil> perfis;

    @Builder(builderMethodName = "UsuarioBuilder")
    public Usuario(Long id,
                   String nome,
                   Pessoa pessoa,
                   String login,
                   String email,
                   String senha,
                   List<Perfil> perfis
    ) {
        this.id = id;
        this.nome = nome;
        this.pessoa = pessoa;
        this.login = login;
        this.email = email;
        this.senha = senha;
        this.perfis = perfis;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
