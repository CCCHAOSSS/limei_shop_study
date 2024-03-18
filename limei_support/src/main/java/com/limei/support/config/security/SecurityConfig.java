package com.limei.support.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 配置过滤链
    * */

    //引入UseDetailService
    private final SysUserDetailService sysUserDetailService;

    public SecurityConfig(SysUserDetailService sysUserDetailService){
        this.sysUserDetailService = sysUserDetailService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //禁用csrf
        http.csrf(csrf -> csrf.disable());
        //配置拦截策略
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/sys").permitAll().anyRequest().authenticated());
        //开启form认证
        http.formLogin(Customizer.withDefaults());
        //配置跨域
        http.cors(cors -> cors.configurationSource(configurationSource()));
        return http.build();
    }



    //创建AuthenticationManager
    @Bean
    public AuthenticationManager sysUserAuthenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(sysUserDetailService);
        return new ProviderManager(provider);   // ProviderManager是实现的一个AuthenticationManager（AM是一个接口）
    }
    //配置密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }

    //配置允许跨域configurationSource
    // 协议不一样 、ip不一样、端口不一样都是跨域
    // 配置哪些主机可以访问
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));  //哪些请求可以访问
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));    //*指都可以访问
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));    //允许的method、header、origin（原始域名）
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;


    }

}
