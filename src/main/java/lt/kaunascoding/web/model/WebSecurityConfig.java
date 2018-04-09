package lt.kaunascoding.web.model;

import lt.kaunascoding.web.model.tables.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {


        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        Duombaze db = new Duombaze();
        List<Users> allUsers = db.getAllUsers();

        ArrayList<UserDetails> protectedUsers = new ArrayList<>();
        for(Users login : allUsers){
            UserDetails user =
                    User.withUsername(login.getUsername())
                            .password(encoder.encode(login.getPassword()))
                            .roles("USER")
                            .build();
            protectedUsers.add(user);
        }


        return new InMemoryUserDetailsManager(protectedUsers);
    }
}