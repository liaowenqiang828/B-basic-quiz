package com.thoughtworks.quizbackend.service;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.GetResourceWithWrongIdException;
import com.thoughtworks.quizbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        User user = User.builder()
                .name("KAMIL")
                .age(24)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing " +
                        "elit. Repellendus, non, dolorem, cumque distinctio magni " +
                        "quam expedita velit laborum sunt amet facere tempora ut " +
                        "fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .build();
        userRepository.save(user);
    }

    public User getUserById(long id) {
        if (id >= userRepository.findAll().size()){
            throw new GetResourceWithWrongIdException(ErrorMessageConstants.GET_USER_BY_WRONG_ID_ERROR + id);
        }
        return userRepository.findById(id);
    }

    public long createUser(User user) {
        return userRepository.save(user);
    }
}
