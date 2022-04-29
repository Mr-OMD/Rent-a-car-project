package com.omertdemirel.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.omertdemirel.rentacar.entities.concretes.Damage;

@Repository
public interface DamageDao extends JpaRepository<Damage, Integer>{

	boolean existsByDamageDescription(String damageDescription);
	
	boolean existsByDamageId(int carDamageId);
}
