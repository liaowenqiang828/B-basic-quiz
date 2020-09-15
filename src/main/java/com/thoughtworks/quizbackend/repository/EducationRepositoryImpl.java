package com.thoughtworks.quizbackend.repository;

import com.thoughtworks.quizbackend.domian.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
public class EducationRepositoryImpl implements EducationReposition {
    private List<Education> educationList = new ArrayList<>();

    @Override
    public void save(Education education) {
        educationList.add(education);
    }

    @Override
    public List<Education> findByUserId(long userId) {
        return educationList
                .stream()
                .filter(item -> userId == item.getUserId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Education> findAll() {
        return educationList;
    }
}
