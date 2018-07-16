package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import domain.UserSpace;
import repositories.UserSpaceRepository;

public class StringToUserSpaceConverter implements Converter<String, UserSpace>{

	@Autowired
	UserSpaceRepository arRepository;

	@Override
	public UserSpace convert(String text) {
		UserSpace result;
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
