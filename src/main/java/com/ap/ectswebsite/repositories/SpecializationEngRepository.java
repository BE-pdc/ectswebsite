package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.SpecializationEng;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpecializationEngRepository extends CrudRepository<SpecializationEng,Integer> {

    public List<SpecializationEng> findAllBySchoolYear(String schoolYear);
}
