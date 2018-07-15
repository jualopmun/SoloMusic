package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Advertisement;

@Repository
public interface AdvertisermentRepository extends JpaRepository<Advertisement, Integer> {

}
