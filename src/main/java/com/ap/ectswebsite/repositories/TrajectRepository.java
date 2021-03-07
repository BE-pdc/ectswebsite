package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.Traject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrajectRepository extends CrudRepository<Traject,Integer> {

    public List<Traject> findAllBySchoolYear (String schoolYear);

    public Traject findBySchoolYearAndOpltraject(String schoolYear, int opltraject);
}
