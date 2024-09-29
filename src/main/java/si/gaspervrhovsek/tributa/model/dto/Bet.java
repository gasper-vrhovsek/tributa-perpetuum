package si.gaspervrhovsek.tributa.model.dto;

import java.math.BigDecimal;

public record Bet(BigDecimal playedAmount, BigDecimal odd, BigDecimal betOutcome, BigDecimal winnings) {
}
