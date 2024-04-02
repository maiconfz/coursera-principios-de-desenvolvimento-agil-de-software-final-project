package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.data.model.Leitor;
import lombok.Data;

@Data
public class AppUserDetails implements UserDetails {

    private final Leitor leitor;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("USER");
    }

    @Override
    public String getPassword() {
        return this.leitor.getSenha();
    }

    @Override
    public String getUsername() {
        return this.leitor.getEmail();
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
