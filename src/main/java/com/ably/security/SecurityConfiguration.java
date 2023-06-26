package com.ably.security;

import com.ably.dto.user.type.RoleType;
import com.ably.exception.ApiError;
import com.ably.exception.ApiException;
import com.ably.repository.MemberRepository;
import com.ably.security.filter.JwtAuthFilter;
import com.ably.security.filter.exception.AccessDeniedHandlerImpl;
import com.ably.security.filter.exception.AuthExceptionHandler;
import com.ably.security.filter.exception.AuthenticationEntryPointImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final String baseUrl = "/ably/api";

    private final JwtProvider jwtProvider;
    private final AuthExceptionHandler authExceptionHandler;
    private final AccessDeniedHandlerImpl accessDeniedHandlerImpl;
    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final MemberRepository memberRepository;

    public HttpSecurity commonConfigure(HttpSecurity http) throws Exception{
        return http
                .httpBasic(e -> e.disable())
                .cors(e -> e.configurationSource(corsConfigurationSource()))
                .csrf(e -> e.disable())
                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e -> {
                    e.authenticationEntryPoint(authenticationEntryPoint);
                    e.accessDeniedHandler(accessDeniedHandlerImpl);
                });
    }

    @Bean
    public SecurityFilterChain securityFilterChainForJwtAuth(HttpSecurity http) throws Exception{
        return commonConfigure(http)
                .authorizeHttpRequests(e -> e
                        .requestMatchers(
                                baseUrl + "/member/withdrawal",
                                baseUrl + "/member/my-info",
                                baseUrl + "/wish/**",
                                baseUrl + "/wish-draw/**")
                        .hasAuthority(RoleType.USER_ROLE.name())
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtAuthFilter(jwtProvider, authExceptionHandler), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> new AuthUser(memberRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(ApiError.NOT_EXIST_DATA, "존재하지 않는 사용자 입니다.")));
    }

    private CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer(){
        return web -> web.ignoring()
                .requestMatchers(
                        PathRequest.toH2Console(),
                        PathRequest.toStaticResources().atCommonLocations()
                );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
