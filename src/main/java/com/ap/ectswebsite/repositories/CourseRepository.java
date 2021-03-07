package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,Integer> {

    public List<Course> findAllBySchoolYear(String schoolYear);
}
