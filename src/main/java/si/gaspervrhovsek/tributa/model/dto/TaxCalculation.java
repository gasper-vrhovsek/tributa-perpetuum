package si.gaspervrhovsek.tributa.model.dto;

import java.math.BigDecimal;

public record TaxCalculation(
        BigDecimal possibleReturnAmount,
        BigDecimal possibleReturnAmountBefTax,
        BigDecimal possibleReturnAmountAfterTax,
        BigDecimal taxRate,
        BigDecimal taxAmount
) {
}
