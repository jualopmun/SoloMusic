package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import domain.PlayList;
import repositories.PlayListRepository;

public class StringToPlayListConverter implements Converter<String,PlayList>{

	@Autowired
	PlayListRepository arRepository;


	@Override
	public PlayList convert(String text) {
		PlayList result;
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
