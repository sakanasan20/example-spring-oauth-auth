package tw.niq.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().authenticated())
			.formLogin(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		UserDetails userDetails = User.withUsername("spring")
				.password(passwordEncoder.encode("password"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
	
}
