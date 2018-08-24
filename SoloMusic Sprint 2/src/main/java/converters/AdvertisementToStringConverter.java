
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Advertisement;

@Component
@Transactional
public class AdvertisementToStringConverter implements Converter<Advertisement, String> {

	@Override
	public String convert(final Advertisement ar) {
		String res;
		if (ar == null)
			res = null;
		else
			res = String.valueOf(ar.getId());
		return res;
	}

}
