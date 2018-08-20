package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import domain.Genre;

@Component
@Transactional
public class GenreToStringConverter implements Converter<Genre, String>{
	
	@Override
	public String convert(final Genre g) {
		String res;
		if (g == null)
			res = null;
		else
			res = String.valueOf(g.getId());
		return res;
	}
}
