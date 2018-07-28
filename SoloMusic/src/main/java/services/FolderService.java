
package services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FolderRepository;
import domain.Folder;
import domain.MailMessage;

@Service
@Transactional
public class FolderService {

	@Autowired
	private FolderRepository	folderRepository;


	//Services
	//	@Autowired
	//	private UserService	userService; Aqui iria el User

	//
	//	@Autowired
	//	private ActorService			actorService;

	public Folder create() {
		final Folder folder = new Folder();

		folder.setFolderName(new String());
		folder.setMessages(new ArrayList<MailMessage>());

		return folder;
	}

	public List<Folder> createDefaultFolders() {
		final List<Folder> folders = new ArrayList<Folder>();

		final Folder inbox = this.create();
		inbox.setFolderName("Inbox");
		inbox.setMessages(new LinkedList<MailMessage>());

		final Folder outbox = this.create();
		outbox.setFolderName("Outbox");
		outbox.setMessages(new LinkedList<MailMessage>());

		final Folder trashbox = this.create();
		trashbox.setFolderName("Trashbox");
		trashbox.setMessages(new LinkedList<MailMessage>());

		final Folder spambox = this.create();
		spambox.setFolderName("Spambox");
		spambox.setMessages(new LinkedList<MailMessage>());

		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		folders.add(spambox);

		return folders;
	}

	//	public Actor selectByUsername(final String username) {
	//		return this.folderRepository.selectByUsername(username);
	//	}

	//	public List<Folder> findAllFolder(final Integer actorId) {
	//		Assert.notNull(actorId);
	//		final Actor actor = this.actorService.findByPrincipal();
	//
	//		final List<Folder> folders = new ArrayList<Folder>();
	//
	//		for (final Folder f : actor.getFolders())
	//			folders.addAll(FolderService.obtenerSubCarpetas(f));
	//		return folders;
	//
	//	}

	//	public Folder saveCreate(final Folder folder) {
	//		Assert.notNull(folder);
	//
	//		final Folder saved = this.folderRepository.save(folder);
	//		final UserAccount userAccount = LoginService.getPrincipal();
	//
	//		final Actor actor = this.folderRepository.selectByUsername(userAccount.getUsername());
	//		actor.getFolders().add(saved);
	//
	//		if (actor instanceof Buyer)
	//			this.buyerService.save((Buyer) actor);
	//		else if (actor instanceof Seller)
	//			this.sellerService.save((Seller) actor);
	//		else if (actor instanceof Company)
	//			this.companyService.save((Company) actor);
	//		else if (actor instanceof Particular)
	//			this.particularService.save((Particular) actor);
	//		else if (actor instanceof Auditor)
	//			this.auditorService.save((Auditor) actor);
	//		else
	//			this.administratorService.save((Administrator) actor);
	//
	//		return saved;
	//	}

	public Folder save(final Folder entity) {
		return this.folderRepository.save(entity);
	}

	public List<Folder> save(final Iterable<Folder> entities) {
		return this.folderRepository.save(entities);
	}

	//	public void delete(final Folder entity) {
	//		Assert.notNull(entity);
	//
	//		final UserAccount userAccount = LoginService.getPrincipal();
	//		final Actor actor = this.folderRepository.selectByUsername(userAccount.getUsername());
	//		actor.getFolders().remove(entity);
	//		if (actor instanceof Buyer)
	//			this.buyerService.save((Buyer) actor);
	//		else if (actor instanceof Seller)
	//			this.sellerService.save((Seller) actor);
	//		else if (actor instanceof Company)
	//			this.companyService.save((Company) actor);
	//		else if (actor instanceof Particular)
	//			this.particularService.save((Particular) actor);
	//		else if (actor instanceof Auditor)
	//			this.auditorService.save((Auditor) actor);
	//		else
	//			this.administratorService.save((Administrator) actor);
	//		this.mailmessageService.delete(entity.getMessages());
	//		this.folderRepository.delete(entity);
	//	}

	public void delete(final Iterable<Folder> entities) {
		this.folderRepository.delete(entities);
	}

	public void flush() {
		this.folderRepository.flush();
	}

	public Folder findOne(final Integer id) {
		return this.folderRepository.findOne(id);
	}

	/**
	 * Metodo recursivo que se utilizará para meterse por todas las ramas del arbol de carpetas y sacar una lista con todas las
	 * carpetas que tenga un actor asociadas
	 * 
	 * @param folder
	 * @return Lista de todas las carpetas que tiene un actor
	 */
	private static List<Folder> obtenerSubCarpetas(final Folder folder) {
		final List<Folder> folders = new ArrayList<Folder>();

		for (final Folder f : folder.getFolderChildren())
			if (!f.getFolderChildren().isEmpty())
				folders.addAll(FolderService.obtenerSubCarpetas(f));
			else
				folders.add(f);
		if (!folder.getFolderChildren().isEmpty())
			folders.add(folder);
		return folders;
	}

}
