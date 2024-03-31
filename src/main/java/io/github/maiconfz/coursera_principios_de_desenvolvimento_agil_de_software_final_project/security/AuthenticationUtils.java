package io.github.maiconfz.coursera_principios_de_desenvolvimento_agil_de_software_final_project.security;

import org.springframework.security.core.Authentication;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtils {

    public static AppUserDetails getAppUserDetails(Authentication authentication) {
        return (AppUserDetails) authentication.getPrincipal();
    }

}
