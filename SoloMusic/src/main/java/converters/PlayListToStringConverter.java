package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.PlayList;

@Component
@Transactional
public class PlayListToStringConverter implements Converter<PlayList, String>{
	@Override
	public String convert(final PlayList ar) {
		String res;
		if (ar == null)
			res = null;
		else
			res = String.valueOf(ar.getId());
		return res;
	}
}
