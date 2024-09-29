package si.gaspervrhovsek.tributa.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

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
}
