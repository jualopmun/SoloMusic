package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PlayList;

@Repository
public interface PlayListRepository  extends JpaRepository<PlayList, Integer> {

}
