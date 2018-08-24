
package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Comment;
import domain.UserSpace;
import repositories.CommentRepository;
import security.LoginService;

@Service
@Transactional
public class CommnentService {

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private LoginService		loginService;

	@Autowired
	private UserSpaceService	userSpaceService;


	public CommnentService() {
		super();
	}

	public Comment create() {

		Comment comment = new Comment();
		comment.setText(new String());
		comment.setPuntuacion(new Integer(0));

		return comment;
	}

	public Comment save(Comment comment, Integer userSpaceID) {

		Assert.notNull(comment);
		Comment m = null;
		UserSpace userSpace = userSpaceService.findOne(userSpaceID);

		Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if (this.exists(comment.getId())) {
			Assert.isTrue(comment.getActor().equals(man));
			m = this.findOne(comment.getId());

			m.setText(comment.getText());
			m.setPuntuacion(comment.getPuntuacion());
			m.setDate(new Date());

			m = commentRepository.save(m);
		} else {
			comment.setActor(man);
			comment.setDate(new Date());
			m = this.commentRepository.save(comment);
			userSpace.getComments().add(m);
			userSpaceService.save(userSpace);

		}
		return m;
	}

	public Comment findOne(Integer id) {
		return commentRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		return commentRepository.exists(id);
	}

	public void delete(Comment comment, Integer userSpaceID) {
		Actor man = this.loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Assert.isTrue(comment.getActor().equals(man));
		commentRepository.delete(comment);
		UserSpace userSpace = userSpaceService.findOne(userSpaceID);
		userSpace.getComments().remove(comment);
		userSpaceService.save(userSpace);
	}

}
