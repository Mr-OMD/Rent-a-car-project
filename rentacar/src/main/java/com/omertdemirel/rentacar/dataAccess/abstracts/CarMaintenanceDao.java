package com.omertdemirel.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.omertdemirel.rentacar.entities.concretes.CarMaintenance;

@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer> {

	boolean existsByCarMaintenanceDescription(String description);
	
	List<CarMaintenance>  getCarMaintenanceByCarMaintenanceCar_CarId(int carId);
	
	CarMaintenance findByCarMaintenanceId(int id);
}
