package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysema.query.types.Predicate;
import com.test.domain.City;
import com.test.domain.CityRepository;

@RestController
@Controller
@RequestMapping("/")
public class CityController {

	@Autowired
	private MyBean myBean;

	@Autowired
	private CityRepository cityRepository;

/*	@RequestMapping("/")
	String home() {
		return "Hello World!" + myBean.example();
	}*/

	@RequestMapping("/insertMongo")
	String insetMongo(String name, String state) {
		City city = new City(name, state);
		cityRepository.save(city);
		System.out.println(city);
		return "Hello " + cityRepository.findByNameAllIgnoringCase("usa2");
	}

	@RequestMapping("/mongo")
	String mongo() {
		return "Hello " + cityRepository.findByNameAllIgnoringCase("usa");
	}

	@RequestMapping("/mongoPage")
	String mongoPage() {
		PageRequest pageable = new PageRequest(1, 3);
		return "Hello " + cityRepository.findAll(pageable).getContent();
	}

	@RequestMapping("city/{id}")
	public City showCityForm(@PathVariable("id") City city, Model model) {

		model.addAttribute("city", city);
		return city;
	}

	@RequestMapping("cities")
	public Object showCities(Model model, Pageable pageable) {

		model.addAttribute("cities", cityRepository.findAll(pageable));
		return model.asMap().get("cities");
	}

	@RequestMapping("queryCity")
	public Object queryCity(Model model, Pageable pageable, @QuerydslPredicate(root = City.class) Predicate predicate,
			@RequestParam MultiValueMap<String, String> parameters) {

		model.addAttribute("cities", cityRepository.findAll(predicate, pageable));
		System.out.println("parameters:>>>" + parameters);
		return model.asMap().get("cities");
	}
}