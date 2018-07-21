package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.PlayList;
import domain.Track;

@Repository
public interface TrackRepository  extends JpaRepository<Track, Integer> {
	
	@Query("select a from PlayList a join a.tracks b where b.id=?1")
	PlayList comprobarTrack(int id);

}
