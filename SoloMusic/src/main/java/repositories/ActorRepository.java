package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Folder;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
