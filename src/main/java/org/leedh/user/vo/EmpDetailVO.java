package org.leedh.user.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class EmpDetailVO implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Setter @Getter
    private String empEmail;
    @Setter @Getter
    private String empPw;
    @Setter @Getter
    private String empNm;
    private List<GrantedAuthority> authorities; // 권한

    // setter
    public void setAuthorities(List<String> authList) {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String s : authList) {
            authorities.add(new SimpleGrantedAuthority(s));
        }

        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return empPw;
    }

    @Override
    public String getUsername() {
        return empEmail;
    }

    @Override // 권한
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}
