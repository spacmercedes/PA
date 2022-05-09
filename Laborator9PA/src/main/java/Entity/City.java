package Entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CITIES")
@NamedQueries({
        @NamedQuery(name="findCityByName", query ="select a from City a where a.name=:name"),
})
public class City {
    @Id
    @SequenceGenerator(name = "sequence",allocationSize=1,
            sequenceName = "city_id_auto")
    @GeneratedValue(generator = "sequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "COUNTRY", length = 100)
    private String country;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CAPITAL")
    private Long capital;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    public City(String name){
        this.name=name;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCapital() {
        return capital;
    }

    public void setCapital(Long capital) {
        this.capital = capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
