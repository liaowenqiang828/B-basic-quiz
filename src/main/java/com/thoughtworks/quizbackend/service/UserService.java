package com.thoughtworks.quizbackend.service;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.IdNotMatchedException;
import com.thoughtworks.quizbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        User user = userRepository.findById(id);
        isUserExist(user, id);
        return user;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    private void isUserExist(User user, long userId) {
        if (Objects.isNull(user)) {
            throw new IdNotMatchedException(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + userId);
        }
    }
}
