package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.Education;
import com.thoughtworks.quizbackend.domian.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EducationReposition extends CrudRepository<Education, Integer> {
    List<Education> findAll();

    List<Education> findAllByUser(User user);
}
