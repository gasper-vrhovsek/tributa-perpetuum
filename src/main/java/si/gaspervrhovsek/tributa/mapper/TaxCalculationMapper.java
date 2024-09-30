package si.gaspervrhovsek.tributa.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import si.gaspervrhovsek.tributa.model.dto.TaxCalculation;
import si.gaspervrhovsek.tributa.model.rest.TaxationResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TaxCalculationMapper {
    @Mapping(target = "possibleReturnAmount", source = "possibleReturnAmount")
    @Mapping(target = "possibleReturnAmountBefTax", source = "possibleReturnAmountBefTax")
    @Mapping(target = "possibleReturnAmountAfterTax", source = "possibleReturnAmountAfterTax")
    @Mapping(target = "taxRate", source = "taxRate")
    @Mapping(target = "taxAmount", source = "taxAmount")
    public abstract TaxationResponse toResponse(TaxCalculation taxCalculation);

    @AfterMapping
    protected TaxationResponse setScale(@MappingTarget TaxationResponse response) {
        return new TaxationResponse(
                setScale(response.possibleReturnAmount()),
                setScale(response.possibleReturnAmountBefTax()),
                setScale(response.possibleReturnAmountAfterTax()),
                setScale(response.taxRate()),
                setScale(response.taxAmount())
        );
    }

    private BigDecimal setScale(BigDecimal value) {
        return value != null ? value.setScale(4, RoundingMode.HALF_UP) : null;
    }
}
