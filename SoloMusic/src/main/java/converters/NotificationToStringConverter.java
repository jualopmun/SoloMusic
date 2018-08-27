
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Notification;

@Component
@Transactional
public class NotificationToStringConverter implements Converter<Notification, String> {

	@Override
	public String convert(final Notification ar) {
		String res;
		if (ar == null)
			res = null;
		else
			res = String.valueOf(ar.getId());
		return res;
	}

}
