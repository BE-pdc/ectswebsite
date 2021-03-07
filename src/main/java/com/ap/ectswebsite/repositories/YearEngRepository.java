package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.YearEng;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface YearEngRepository extends CrudRepository<YearEng,Integer> {

    public List<YearEng> findAllBySchoolYear(String schoolYear);

    public YearEng findBySchoolYearAndTraject(String schoolYear, int traject);

}
