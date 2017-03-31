package es.json.examples.serializers;

import com.google.common.collect.ImmutableMap;
import es.json.examples.model.Address;
import es.json.examples.model.Country;
import es.json.examples.model.Person;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.util.Arrays.asList;

public class PersonTestUtils {
	public static Person given_a_person() {
		return new Person.Builder()
				.name("name")
				.lastName("lastName")
				.age(30)
				.birthDate(LocalDate.of(2017, 3, 30))
				.salary(BigDecimal.ONE)
				.other(ImmutableMap.of("job_description", ImmutableMap.of("company","company","position","position")))
				.address(new Address.Builder()
						.address("address")
						.city("city")
						.state("state")
						.zipcode("zipCode")
						.tags(asList("tag1", "tag2"))
						.country(new Country.Builder()
								.country("country")
								.countryISO("countryISO")
								.build())
						.build())
				.build();
	}
}
