package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    long save(User user);
    User findById(long id);
    List<User> findAll();
}
