package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.BlockEng;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlockEngRepository extends CrudRepository<BlockEng,Integer> {

    boolean existsByid(Integer id);

    public List<BlockEng> findAllBySchoolYear(String schoolYear);
}
