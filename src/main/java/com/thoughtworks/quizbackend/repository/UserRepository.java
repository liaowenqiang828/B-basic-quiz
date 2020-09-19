package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);
    List<User> findAll();
}
