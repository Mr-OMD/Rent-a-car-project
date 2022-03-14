package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.omertdemirel.rentacar.entities.concretes.AdditionalService;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
	List<AdditionalService> getAllByRental(int rentId);

	boolean existsByName(String name);
}
