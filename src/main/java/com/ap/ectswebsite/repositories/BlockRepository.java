package com.ap.ectswebsite.repositories;

import com.ap.ectswebsite.entities.Block;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends CrudRepository<Block,Integer> {

    boolean existsByid(Integer id);

    public List<Block> findAllBySchoolYear(String schoolYear);
}
