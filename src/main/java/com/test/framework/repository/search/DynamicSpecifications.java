package com.test.framework.repository.search;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.DefaultConversionService;

import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.PathBuilderFactory;
import com.test.framework.converter.StringToDateConverter;

public class DynamicSpecifications {
	private static final ConversionService conversionService = new DefaultConversionService();

	private static final Map<Class<?>, PathBuilder<?>> PATH_BUILDER_MAP = new HashMap<>();
	static {
		((ConverterRegistry) conversionService).addConverter(String.class, Date.class, new StringToDateConverter());
	}

	public static PathBuilder<?> getPathBuilder(Class<?> clazz) {
		if (!PATH_BUILDER_MAP.containsKey(clazz)) {
			PATH_BUILDER_MAP.put(clazz, new PathBuilderFactory().create(clazz));
		}
		return PATH_BUILDER_MAP.get(clazz);
	}

/*	public static Predicate bySearchFilter(final Collection<SearchFilter> filters,
			final Class<?> entityClazz) {
		PathBuilder<?> pb = getPathBuilder(entityClazz);
		
			
				if (filters != null && filters.size() > 0) {

					List<Predicate> predicates = Lists.newArrayList();
					for (SearchFilter filter : filters) {

						// nested path translate
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path<String> expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// convert value
						Class<?> attributeClass = expression.
						if (filter.operator.equals(SearchFilter.Operator.ISN)
								|| filter.operator.equals(SearchFilter.Operator.ISNN)) {

						} else if (!attributeClass.equals(String.class) && filter.value instanceof String
								&& conversionService.canConvert(String.class, attributeClass)) {
							filter.value = conversionService.convert(filter.value, attributeClass);
						}

						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression, filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case LLIKE:
							predicates.add(builder.like(expression, filter.value + "%"));
							break;
						case RLIKE:
							predicates.add(builder.like(expression, "%" + filter.value));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case ISN:
							predicates.add(builder.isNull(expression));
							break;
						case ISNN:
							predicates.add(builder.isNotNull(expression));
							break;
						}

				}

				
	}
	}*/
}
