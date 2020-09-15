package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private List<User> userList = new ArrayList<>();

    @Override
    public long save(User user) {
        int index = userList.size();
        user.setId(index + 1);
        userList.add(user);
        return index + 1;
    }

    @Override
    public User findById(long id) {
        return userList.get((int) (id - 1));
    }

    @Override
    public List<User> findAll() {
        return userList;
    }
}
