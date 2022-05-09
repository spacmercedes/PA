package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COUNTRIES")
@NamedQueries({
        @NamedQuery(name="findCountryByName", query ="select a from Country a where a.name=:name"),
})
public class Country {
    @Id
    @SequenceGenerator(name = "sequence",allocationSize=1,
            sequenceName = "country_id_auto")
    @GeneratedValue(generator = "sequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CODE")
    private Long code;

    @Column(name = "CONTINENT")
    private Long continent;

    @OneToMany(targetEntity = City.class)
    private List cities;
//
//    @ManyToOne(targetEntity = Continent.class)
//    private String continent1;

    public List getCities() {
        return cities;
    }

    public void setCities(List cities) {
        this.cities = cities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getContinent() {
        return continent;
    }

    public void setContinent(Long continent) {
        this.continent = continent;
    }

}
