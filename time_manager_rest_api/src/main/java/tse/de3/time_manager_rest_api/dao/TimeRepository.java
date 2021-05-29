package tse.de3.time_manager_rest_api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tse.de3.time_manager_rest_api.domain.Time;

public interface TimeRepository extends JpaRepository<Time, Integer> {

}