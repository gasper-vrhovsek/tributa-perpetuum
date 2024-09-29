package si.gaspervrhovsek.tributa.service;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.gaspervrhovsek.tributa.model.db.Trader;
import si.gaspervrhovsek.tributa.model.dto.TaxCalculation;
import si.gaspervrhovsek.tributa.model.dto.Bet;

@ApplicationScoped
public class TaxationService {
    TaxCalculationService taxCalculationService;

    @Inject
    public TaxationService(final TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    public TaxCalculation getTax(final UUID traderId, final BigDecimal playedAmount, final BigDecimal odd) {
        final var trader = (Trader) Trader.findById(traderId);
        final var location = trader.location;

        final var bet = calcBet(playedAmount, odd);

        final var taxCalculation = taxCalculationService.calcTax(bet, location.taxType, location.taxCalcType, location.tax);
        System.out.println("Tax calculation = " + taxCalculation);
        return taxCalculation;
    }

    private Bet calcBet(final BigDecimal playedAmount, final BigDecimal odd) {
        final var betOutcome = playedAmount.multiply(odd);
        final var winnings = betOutcome.subtract(playedAmount);

        return new Bet(playedAmount, odd, betOutcome, winnings);
    }
}
