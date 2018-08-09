package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;
import domain.Folder;
import repositories.CommentRepository;
import repositories.FolderRepository;
import security.Authority;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Comment> {

	@Autowired
	CommentRepository arRepository;

	@Override
	public Comment convert(String text) {
		Comment result;
		int id;
		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = arRepository.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
