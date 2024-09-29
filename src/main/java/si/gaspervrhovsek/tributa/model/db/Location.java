package si.gaspervrhovsek.tributa.model.db;

import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import si.gaspervrhovsek.tributa.model.enums.TaxCalcType;
import si.gaspervrhovsek.tributa.model.enums.TaxType;

@Entity
@Table(name = "locations")
public class Location extends PanacheEntityBase {
    @Id
    @Column(name = "id", length = 40)
    public UUID id;

    @Column(name = "name")
    public String name;

    @Column(name = "tax_type", columnDefinition = "tax_type")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    public TaxType taxType;

    @Column(name = "tax_calc", columnDefinition = "tax_calc_type")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    public TaxCalcType taxCalcType;

    @Column(name = "tax")
    public BigDecimal tax;
}
