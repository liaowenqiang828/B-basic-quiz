package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.Education;
import org.springframework.stereotype.Repository;

import java.util.List;

// GTB: + Repository 单独提取了接口，后续可以将接口和实现放到不同的包里
public interface EducationReposition {
    void save(Education education);

    List<Education> findByUserId(long userId);

    List<Education> findAll();
}
