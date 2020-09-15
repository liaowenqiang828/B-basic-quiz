package com.thoughtworks.quizbackend.service;

import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.repository.EducationReposition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private EducationReposition educationRepository;
    public EducationService(EducationReposition educationRepository) {
        this.educationRepository = educationRepository;
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
        return educationRepository.findByUserId(userId);
    }
}
