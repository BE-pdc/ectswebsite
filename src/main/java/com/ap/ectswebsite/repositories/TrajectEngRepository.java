package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.TrajectEng;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrajectEngRepository extends CrudRepository<TrajectEng,Integer> {

    public List<TrajectEng> findAllBySchoolYear(String schoolYear);

    public TrajectEng findBySchoolYearAndOpltraject(String schoolYear, int opltraject);
}
