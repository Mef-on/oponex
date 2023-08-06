package com.oponex.oponexservice.repo;

import com.oponex.oponexservice.model.TireDTO;
import com.oponex.oponexservice.model.dao.Tire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TireRepository extends JpaRepository<Tire, Long>, JpaSpecificationExecutor<Tire> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Tire t " +
            "WHERE t.name = :#{#tireDTO.name} " +
            "AND t.brand = :#{#tireDTO.brand.name()} " +
            "AND t.width = :#{#tireDTO.width} " +
            "AND t.profile = :#{#tireDTO.profile} " +
            "AND t.season = :#{#tireDTO.season.name()} " +
            "AND t.volume = :#{#tireDTO.volume} " +
            "AND t.dateProduction = :#{#tireDTO.dateProduction}")
    boolean existsByProperties(TireDTO tireDTO);
}
