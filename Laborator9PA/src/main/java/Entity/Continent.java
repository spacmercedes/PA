package Entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CONTINENTS")
@NamedQueries({
        @NamedQuery(name="findContinentByName", query ="select a from Continent a where a.name=:name"),
})
public class Continent {
    @Id
    @SequenceGenerator(name = "sequence",allocationSize=1,
            sequenceName = "continent_id_auto")
    @GeneratedValue(generator = "sequence")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    public Continent(){ }
    public Continent(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Country.class)
    private List countries;

    public List getCountries() {
        return countries;
    }

    public void setCountries(List countries) {
        this.countries = countries;
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


}
