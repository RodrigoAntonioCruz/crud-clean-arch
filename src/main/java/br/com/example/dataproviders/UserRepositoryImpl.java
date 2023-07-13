package br.com.example.dataproviders;


import br.com.example.core.dataprovider.UserRepository;
import br.com.example.core.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryImpl extends MongoRepository<User, String>, UserRepository {

}

