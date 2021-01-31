package org.leedh.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

public class SecurityConfig {

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers( "/public/css/**", "/public/js/**", "/public/img/**");
    }

}
