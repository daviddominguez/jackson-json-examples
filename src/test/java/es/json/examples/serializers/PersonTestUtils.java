package es.json.examples.serializers;

import es.json.examples.model.Address;
import es.json.examples.model.Country;
import es.json.examples.model.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.TreeMap;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSortedMap;

public class PersonTestUtils {
	public static Person given_a_person() {
		return new Person.Builder()
				.name("name")
				.lastName("lastName")
				.age(30)
				.birthDate(LocalDate.of(2017, 3, 30))
				.salary(BigDecimal.ONE)
				.unknown(unmodifiableSortedMap(new TreeMap<String, String>() {{
					put("email","personal_email");
					put("job.company.name","company_name");
					put("job.company.address.street.name","company_address_street_name");
					put("job.company.address.street.number","company_address_street_number");
					put("job.company.address.street.floor","company_address_street_floor");
					put("job.company.address.city","company_address_city");
					put("job.company.address.zipCode","company_address_zipCode");
					put("job.company.address.country","company_address_country");
					put("job.position.name","position_name");
					put("job.position.responsibilities","position_responsibilities");
					put("job.phone.number","company_phone");
					put("job.phone.extension","6");
					put("job.email","job_email");
					put("partner.name","partner_name");
					put("partner.birth_date","partner_birthdate");
					put("partner.phone.number", "partner_phone_number");
					put("partner.phone.extension", "partner_phone_extension");
				}}))
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
