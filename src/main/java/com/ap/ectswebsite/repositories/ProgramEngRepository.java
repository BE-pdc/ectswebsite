package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.ProgramEng;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProgramEngRepository extends CrudRepository<ProgramEng,Integer> {

    public List<ProgramEng> findAllBySchoolYear(String schoolYear);

    public ProgramEng findAllBySchoolYearAndProgramCode(String schoolYear, String programCode);
}
