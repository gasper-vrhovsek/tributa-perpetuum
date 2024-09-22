package si.gaspervrhovsek.tributa.model.rest;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaxationRequest(
        @JsonProperty("traderId") int traderId,
        @JsonProperty("playedAmount") BigDecimal playedAmount,
        @JsonProperty("odd") BigDecimal odd
) {
}
