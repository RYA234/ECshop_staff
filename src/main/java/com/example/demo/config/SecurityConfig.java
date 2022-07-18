//package com.example.demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Profile("production")
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig  extends WebSecurityConfigurerAdapter {
//
//
//
//    @Autowired
//    public PasswordEncoder passwordEncoder;
//
//    /** セキュリティの各種設定 */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//
//        // ログイン不要ページ
//        http
//                .authorizeRequests()
//                .antMatchers("/index").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/check/access").permitAll()
//                .anyRequest().authenticated();
//        // ログイン処理
//        http
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/menu/menu", true);
//        http.csrf().disable();
//    }
//    // ログイン認証に使用するユーザー情報を設定
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .inMemoryAuthentication()
//                .withUser("User") // userを追加
//                .password(passwordEncoder.encode("PASS"))
//                .roles("GENERAL");
//    }
//
//
//}
