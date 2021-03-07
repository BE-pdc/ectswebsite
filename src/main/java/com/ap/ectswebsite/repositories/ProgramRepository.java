package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProgramRepository extends CrudRepository<Program,Integer> {

    public List<Program> findAllBySchoolYear(String schoolYear);

    public Program findAllBySchoolYearAndProgramCode(String schoolYear, String programCode);
}
