package gr.aueb.mscis.productCrawlerServer.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeanValidationUtils {
	private BeanValidationUtils(){}
	private static ValidatorFactory factory = null;
	public static Validator getBeanValidator(){
		if (factory == null){
			factory = Validation.buildDefaultValidatorFactory();
		}
		return factory.getValidator();
	}
}
