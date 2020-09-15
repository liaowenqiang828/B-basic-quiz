package com.thoughtworks.quizbackend.service;

import com.thoughtworks.quizbackend.constants.ErrorMessageConstants;
import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.exception.GetResourceWithWrongIdException;
import com.thoughtworks.quizbackend.repository.EducationReposition;
import com.thoughtworks.quizbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private EducationReposition educationRepository;
    private UserRepository userRepository;
    public EducationService(EducationReposition educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
        Education education1 = Education.builder()
                .userId(1)
                .year(2005)
                .title("Secondary school specializing in artistic")
                .description("Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus quaerat " +
                "quibusdam perferendis? Iusto, quibusdam asperiores unde repellat.")
                .build();
        Education education2 = Education.builder()
                .userId(1)
                .year(2009)
                .title("First level graduation in Graphic Design")
                .description("Aspernatur, mollitia, quos maxime eius suscipit sed beatae ducimus quaerat" +
                        " quibusdam perferendis? Iusto, quibusdam asperiores unde repellat.")
                .build();
        educationRepository.save(education1);
        educationRepository.save(education2);
    }

    public List<Education> getEducationListByUserId(long userId) {
        if (userId >= userRepository.findAll().size()) {
            throw new GetResourceWithWrongIdException(ErrorMessageConstants.GET_USER_BY_WRONG_ID_ERROR + userId);
        }
        return educationRepository.findByUserId(userId);
    }

    public void addEducationByUserId(Education education, long userId) {
        if (userId >= userRepository.findAll().size()) {
            throw new GetResourceWithWrongIdException(ErrorMessageConstants.GET_USER_BY_WRONG_ID_ERROR + userId);
        }
        education.setUserId(userId);
        educationRepository.save(education);
    }
}
