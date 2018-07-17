package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Gift;
@Component
@Transactional
public class GiftToStringConverter implements Converter<Gift, String>{
	@Override
	public String convert(final Gift ar) {
		String res;
		if (ar == null)
			res = null;
		else
			res = String.valueOf(ar.getId());
		return res;
	}
}
