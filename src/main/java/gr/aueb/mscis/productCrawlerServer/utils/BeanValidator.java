package gr.aueb.mscis.productCrawlerServer.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import org.apache.log4j.Logger;

public interface BeanValidator<T> {
	default boolean validate(Logger logger){
		try{
			@SuppressWarnings("unchecked")
			Set<ConstraintViolation<T>> constraints = BeanValidationUtils.getBeanValidator().validate((T)this);
			return constraints.isEmpty();
		} catch (IllegalArgumentException | ValidationException e){
			logger.warn("Validation Failed", e);
		}
		return false;
	}
	
	default List<String> getValidatationMessages(Logger logger){
		List<String> messages = new ArrayList<>();
		try{
			@SuppressWarnings("unchecked")
			Set<ConstraintViolation<T>> constraints = BeanValidationUtils.getBeanValidator().validate((T)this);
			constraints.forEach(constraint -> messages.add(constraint.getMessage()));
		} catch (IllegalArgumentException | ValidationException e){
			logger.warn("Validation with message Failed", e);
		}
		return messages;
	}
}
