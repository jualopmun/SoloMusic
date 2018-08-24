
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Genre;
import domain.UserSpace;

@Repository
public interface UserSpaceRepository extends JpaRepository<UserSpace, Integer> {

	//Metodo para buscar userspace
	@Query("select a from UserSpace a where a.title like concat('%',?1,'%')")
	public List<UserSpace> userSpacesearch(String text);
	
	//Metodo para buscar userspace por género
		@Query("select a from UserSpace a where a.genre =?1")
		public List<UserSpace> userSpaceGenreSearch(Genre genre);

}
