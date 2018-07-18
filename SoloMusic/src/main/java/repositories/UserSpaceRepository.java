
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.UserSpace;
import services.UserSpaceService;

@Repository
public interface UserSpaceRepository extends JpaRepository<UserSpace, Integer> {
	
	//Metodo para buscar userspace
	@Query("select a from UserSpace a where a.title =?1")
    public List<UserSpaceService>	userSpacesearch(String text);

}
