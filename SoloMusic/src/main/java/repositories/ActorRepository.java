
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int id);

	@Query("select a from Actor a where a.userSpace.id = ?1")
	Actor findByUserSpaceId(int id);

	@Query("select c from Actor c where c.userAccount.username = ?1")
	Actor encontrarActor(String username);

}
