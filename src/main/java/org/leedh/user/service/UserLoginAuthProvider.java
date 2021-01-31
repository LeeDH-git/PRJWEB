package org.leedh.user.service;

import org.leedh.common.util.BCryptPwEncodingUtil;
import org.leedh.user.vo.EmpDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginAuthProvider implements AuthenticationProvider {

    UserDetailsService userDetailsService;
    BCryptPwEncodingUtil pwEncoder;

    public UserLoginAuthProvider(UserDetailsService userDetailsService, BCryptPwEncodingUtil pwEncoder) {
        this.userDetailsService = userDetailsService;
        this.pwEncoder = pwEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        // 사용자가 입력한 정보
        String empEmail = authentication.getName();
        String empPw = (String) authentication.getCredentials();

        // DB에서 가져온 정보 (커스터마이징 가능)
        EmpDetailVO userDetails = (EmpDetailVO) userDetailsService.loadUserByUsername(empEmail);

        // 인증 진행
        // DB에 정보가 없는 경우 예외 발생 (아이디/패스워드 잘못됐을 때와 동일한 것이 좋음)
        // ID 및 PW 체크해서 안맞을 경우 (matches를 이용한 암호화 체크를 해야함)
        if (userDetails == null || !empEmail.equals(userDetails.getUsername())
                || !pwEncoder.matches(empPw, userDetails.getPassword())) {

            throw new BadCredentialsException(empEmail);

            // 계정 정보 맞으면 나머지 부가 메소드 체크 (이부분도 필요한 부분만 커스터마이징 하면 됨)
            // 잠긴 계정일 경우
        } else if (!userDetails.isAccountNonLocked()) {
            throw new LockedException(empEmail);

            // 비활성화된 계정일 경우
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException(empEmail);

            // 만료된 계정일 경우
        } else if (!userDetails.isAccountNonExpired()) {
            throw new AccountExpiredException(empEmail);

            // 비밀번호가 만료된 경우
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(empEmail);
        }

        // 최종 리턴 시킬 새로만든 Authentication 객체
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        return newAuth;
    }

    @Override
    // authenticate 메소드에서 반환한 사용자 정보(UserDetails)객체가 유효한 타입이 맞는지 검사
    // null 값이거나 잘못된 타입 리턴시 인증 실패
    public boolean supports(Class<?> authentication) {

        // 스프링 Security가 요구하는 UsernamePasswordAuthenticationToken 타입이 맞는지 확인
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
