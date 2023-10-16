package com.example.adapter.output.repository;


import com.example.adapter.output.repository.entity.UserEntity;
import com.example.adapter.output.repository.mapper.UserOutputMapper;
import com.example.core.User;
import com.example.core.port.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {
        UserEntity entity = UserOutputMapper.INSTANCE.toUserData(user);
        entity = mongoTemplate.save(entity);
        return UserOutputMapper.INSTANCE.toDomain(entity);
    }

    @Override
    public Optional<User> findUserById(String id) {
        UserEntity entity = mongoTemplate.findById(id, UserEntity.class);
        return Optional.ofNullable(UserOutputMapper.INSTANCE.toDomain(entity));
    }

    @Override
    public List<User> findByFilter(String query) {
        Criteria criteria = new Criteria();

        List<java.lang.reflect.Field> fields = Arrays.asList(UserEntity.class.getDeclaredFields());
        List<Criteria> fieldCriteria = fields.stream()
                .filter(field -> field.getType().equals(String.class))
                .map(field -> field.getName().equals("id")
                        ? Criteria.where(field.getName()).is(query)
                        : Criteria.where(field.getName()).regex("^" + Pattern.quote(query), "i"))
                .toList();

        criteria.orOperator(fieldCriteria.toArray(new Criteria[0]));

        Query searchQuery = new Query(criteria);

        List<UserEntity> results = mongoTemplate.find(searchQuery, UserEntity.class);

        return results.stream()
                .map(UserOutputMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, UserEntity.class);
    }
}


