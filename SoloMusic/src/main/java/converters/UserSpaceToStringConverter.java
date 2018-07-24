
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.UserSpace;

@Component
@Transactional
public class UserSpaceToStringConverter implements Converter<UserSpace, String> {

	@Override
	public String convert(final UserSpace ar) {
		String res;
		if (ar == null)
			res = null;
		else
			res = String.valueOf(ar.getId());
		return res;
	}

}
