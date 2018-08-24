package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
