package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.Specialization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecializationRepository extends CrudRepository<Specialization,Integer> {

    public List<Specialization> findAllBySchoolYear(String schoolYear);
}
