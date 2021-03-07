package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.CourseEng;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseEngRepository extends CrudRepository<CourseEng,Integer> {

    public List<CourseEng> findAllBySchoolYear(String schoolYear);
}
