package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import domain.Donation;
import repositories.DonationRepository;

public class StringToDonationConverter implements Converter<String,Donation>{

	@Autowired
	DonationRepository arRepository;


	@Override
	public Donation convert(String text) {
		Donation result;
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
