package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Gift;

@Repository
public interface GiftRepository  extends JpaRepository<Gift, Integer> {

}
