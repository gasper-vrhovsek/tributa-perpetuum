package si.gaspervrhovsek.tributa.model.db;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "traders")
public class Trader extends PanacheEntityBase {
    @Id
    @Column(name = "id", length = 40)
    public UUID id;

    @Column(name = "name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    public Location location;
}
