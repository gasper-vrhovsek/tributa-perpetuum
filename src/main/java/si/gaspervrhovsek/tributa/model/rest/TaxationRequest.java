package si.gaspervrhovsek.tributa.model.rest;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TaxationRequest(
        @JsonProperty("traderId") UUID traderId,
        @JsonProperty("playedAmount") BigDecimal playedAmount,
        @JsonProperty("odd") BigDecimal odd
) {
}
