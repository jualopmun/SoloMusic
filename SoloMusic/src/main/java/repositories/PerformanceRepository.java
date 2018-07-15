package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Perfomance;

@Repository
public interface PerformanceRepository  extends JpaRepository<Perfomance, Integer> {

}
