package br.com.zupacademy.laismukai.casadocodigo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistValueValidator implements ConstraintValidator<ExistValue, Object> {

	private String domainAttribute;
	private Class<?> klass;
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExistValue params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		
		Assert.state(list.size() <= 1,
				"Não foi encontrado um(a) " + klass + " com o atributo " + domainAttribute + " = " + value + " no banco de dados");
		//se lista possui valor, existe registro.
		return !list.isEmpty();
	}

}
