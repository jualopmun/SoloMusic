
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Track;

@Component
@Transactional
public class TrackToStringConverter implements Converter<Track, String> {

	@Override
	public String convert(final Track ar) {
		String res;
		if (ar == null)
			res = null;
		else
			res = String.valueOf(ar.getId());
		return res;
	}
}
