package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import domain.Perfomance;
import repositories.PerformanceRepository;

public class StringToPerformanceConverter implements Converter<String,Perfomance>{

	@Autowired
	PerformanceRepository arRepository;


	@Override
	public Perfomance convert(String text) {
		Perfomance result;
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
