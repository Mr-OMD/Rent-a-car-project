package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.Color;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer> {

	Color getByColorName(String name);
	boolean existsByColorName(String name);
	Color findByColorId(int id);
}
