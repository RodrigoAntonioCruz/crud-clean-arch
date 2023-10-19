package com.example.adapter.output.repository;

import com.example.adapter.output.repository.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, String> {
    @Query("{ $or: [ { 'id' : ?0 }, { 'name' : { $regex: ?0, $options: 'i' } }, { 'cpf' : { $regex: ?0, $options: 'i' } }, { 'email' : { $regex: ?0, $options: 'i' } } ] }")
    List<UserEntity> findByFilter(String query);
}
