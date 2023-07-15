package br.com.example.dataproviders;


import br.com.example.core.dataprovider.UserRepository;
import br.com.example.core.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryImpl extends MongoRepository<User, String>, UserRepository {

    @Override
    default List<User> findBySearch(String query) {
        return findUserBySearch(query);
    }

    @Query("{$or: [{_id: ?0}, {name: {$regex: ?0, $options: 'i'}}, {email: {$regex: ?0, $options: 'i'}}]}")
    List<User> findUserBySearch(String query);

}

