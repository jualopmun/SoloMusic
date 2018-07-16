
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.UserSpace;

@Repository
public interface UserSpaceRepository extends JpaRepository<UserSpace, Integer> {

}
