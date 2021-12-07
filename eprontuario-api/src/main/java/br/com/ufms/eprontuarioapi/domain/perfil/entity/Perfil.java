package br.com.ufms.eprontuarioapi.domain.perfil.entity;

import br.com.ufms.eprontuarioapi.domain.perfil.enumeration.EPerfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PERFIL")
@SequenceGenerator(name = "seq_perfil",  allocationSize = 1)
public class Perfil implements GrantedAuthority {

    @Id
    @Column(name = "PER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PER_PERFIL", length = 12)
    @Enumerated(EnumType.STRING)
    private EPerfil perfil;


    @Override
    public String getAuthority() {
        return this.perfil.toString();
    }
}
