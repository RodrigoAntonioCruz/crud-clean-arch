package br.com.example.configuration.app;

import br.com.example.core.dataprovider.UserRepository;
import br.com.example.core.usecase.UserUseCase;
import br.com.example.core.usecase.UserUseCaseUseCaseImpl;
import br.com.example.dataprovider.UserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public UserUseCase userUseCase(UserRepository userRepository) {
        return new UserUseCaseUseCaseImpl(userRepository);
    }
}
