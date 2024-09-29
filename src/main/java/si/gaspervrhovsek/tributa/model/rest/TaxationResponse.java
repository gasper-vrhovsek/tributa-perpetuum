package si.gaspervrhovsek.tributa.model.rest;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaxationResponse(
        @JsonProperty("possibleReturnAmount") BigDecimal possibleReturnAmount,
        @JsonProperty("possibleReturnAmountBefTax,") BigDecimal possibleReturnAmountBefTax,
        @JsonProperty("possibleReturnAmountAfterTax,") BigDecimal possibleReturnAmountAfterTax,
        @JsonProperty("taxRate") BigDecimal taxRate,
        @JsonProperty("taxAmount") BigDecimal taxAmount
) {
}
