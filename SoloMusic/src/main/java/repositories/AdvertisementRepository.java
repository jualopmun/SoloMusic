
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Advertisement;
import domain.UserSpace;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

	
	//Metodo para buscar anuncios
		@Query("select a from Advertisement a where a.title like concat('%',?1,'%')")
		public List<Advertisement> advertisementSearch(String text);
}
