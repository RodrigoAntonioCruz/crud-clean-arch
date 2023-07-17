package br.com.example.dataproviders.repositoryImpl;


import br.com.example.core.dataprovider.UserRepository;
import br.com.example.core.entity.User;
import br.com.example.dataproviders.data.UserData;
import br.com.example.entrypoints.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {
        UserData userData = UserMapper.INSTANCE.toUserData(user);
        userData = mongoTemplate.save(userData);
        return UserMapper.INSTANCE.toDomain(userData);
    }

    @Override
    public Optional<User> findUserById(String id) {
        UserData userData = mongoTemplate.findById(id, UserData.class);
        return Optional.ofNullable(UserMapper.INSTANCE.toDomain(userData));
    }

    @Override
    public List<User> findBySearch(String query) {
        Criteria criteria = new Criteria();

        List<java.lang.reflect.Field> fields = Arrays.asList(UserData.class.getDeclaredFields());
        List<Criteria> fieldCriteria = fields.stream()
                .filter(field -> field.getType().equals(String.class))
                .map(field -> field.getName().equals("id")
                        ? Criteria.where(field.getName()).is(query)
                        : Criteria.where(field.getName()).regex("^" + Pattern.quote(query), "i"))
                .toList();

        criteria.orOperator(fieldCriteria.toArray(new Criteria[0]));

        Query searchQuery = new Query(criteria);

        List<UserData> results = mongoTemplate.find(searchQuery, UserData.class);

        return results.stream()
                .map(UserMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, UserData.class);
    }
}


