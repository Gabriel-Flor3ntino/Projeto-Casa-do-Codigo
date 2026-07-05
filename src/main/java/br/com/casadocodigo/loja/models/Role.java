package br.com.casadocodigo.loja.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role implements GrantedAuthority {

    @Id
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
