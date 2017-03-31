package es.json.examples.model;

import com.google.common.base.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Country {

    private String country;
    private String countryISO;

    public Country () {
	}

	private Country(Builder builder) {
		setCountry(builder.country);
		setCountryISO(builder.countryISO);
	}

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryISO() {
        return countryISO;
    }

    public void setCountryISO(String countryISO) {
        this.countryISO = countryISO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country1 = (Country) o;
        return Objects.equal(country, country1.country) &&
                Objects.equal(countryISO, country1.countryISO);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), country, countryISO);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("country", country)
                .add("countryISO", countryISO)
                .toString();
    }

	public static final class Builder {
		private String country;
		private String countryISO;

		public Builder() {
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public Builder countryISO(String countryISO) {
			this.countryISO = countryISO;
			return this;
		}

		public Country build() {
			return new Country(this);
		}
	}
}
