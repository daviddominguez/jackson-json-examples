package es.json.examples.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.common.base.Objects;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Objects.equal;

public class Person {

    private String name;
    private String lastName;
    private Address address;

    private LocalDate birthDate;

    private Integer age;
    private BigDecimal salary;

    private Map<String, Object> other = new HashMap<>();

    public Person() {
	}

	private Person(Builder builder) {
		setName(builder.name);
		setLastName(builder.lastName);
		setAddress(builder.address);
		setBirthDate(builder.birthDate);
		setAge(builder.age);
		setSalary(builder.salary);
		other = builder.other;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @JsonAnyGetter
    public Map<String,Object> any() {
        return other;
    }

    private boolean isDocument(String key) {
    	for (String entryKey : other.keySet()) {
    		if (entryKey.endsWith(key))
    			return false;
		}
		return true;
	}

	@JsonAnySetter
    public void set(String name, Object value) {
		if (value instanceof Map) {
			Map<String, Object> values = (Map) value;
			for (Map.Entry<String, Object> entry : values.entrySet()) {
				set(name + "." + entry.getKey(), entry.getValue());
			}
		}
		else if (value instanceof String) {
			other.put(name, value);
		}
		else {
			throw new IllegalArgumentException();
		}
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return equal(name, person.name) &&
                equal(lastName, person.lastName) &&
                equal(address, person.address) &&
                equal(birthDate, person.birthDate) &&
                equal(age, person.age) &&
                equal(salary, person.salary) &&
				equal(other, person.other);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), name, lastName, address, birthDate, age, salary, other);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("name", name)
                .add("lastName", lastName)
                .add("address", address)
                .add("birthDate", birthDate)
                .add("age", age)
                .add("salary", salary)
				.add("other", other)
                .toString();
    }

	public static final class Builder {
		private String name;
		private String lastName;
		private Address address;
		private LocalDate birthDate;
		private Integer age;
		private BigDecimal salary;
		private Map<String, Object> other;

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder address(Address address) {
			this.address = address;
			return this;
		}

		public Builder birthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public Builder age(Integer age) {
			this.age = age;
			return this;
		}

		public Builder salary(BigDecimal salary) {
			this.salary = salary;
			return this;
		}

		public Builder other(Map<String, Object> other) {
			this.other = other;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}
}
