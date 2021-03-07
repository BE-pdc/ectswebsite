package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.Year;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface YearRepository extends CrudRepository<Year,Integer> {

    public List<Year> findAllBySchoolYear(String schoolYear);

    public Year findBySchoolYearAndTraject(String schoolYear, int traject);
}
