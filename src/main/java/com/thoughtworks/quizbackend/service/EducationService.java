package com.thoughtworks.quizbackend.service;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.domian.User;
import com.thoughtworks.quizbackend.exception.IdNotMatchedException;
import com.thoughtworks.quizbackend.repository.EducationReposition;
import com.thoughtworks.quizbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private EducationReposition educationRepository;
    private UserRepository userRepository;
    @Autowired
    public EducationService(EducationReposition educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public List<Education> getEducationListByUserId(long userId) {
        if (userId > userRepository.findAll().size()) {
            throw new IdNotMatchedException(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + userId);
        }
        return educationRepository.findAllByUser(userRepository.findById(userId));
    }

    public void addEducationByUserId(Education education, long userId) {
        if (userId > userRepository.findAll().size()) {
            throw new IdNotMatchedException(ErrorMessageConstants.ID_NOT_MATCHED_ERROR + userId);
        }
        User user = userRepository.findById(userId);
        education.setUser(user);
        educationRepository.save(education);
    }
}
