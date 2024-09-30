package si.gaspervrhovsek.tributa.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.enterprise.context.ApplicationScoped;
import si.gaspervrhovsek.tributa.model.dto.Bet;
import si.gaspervrhovsek.tributa.model.dto.TaxCalculation;
import si.gaspervrhovsek.tributa.model.enums.TaxCalcType;
import si.gaspervrhovsek.tributa.model.enums.TaxType;

@ApplicationScoped
public class TaxCalculationService {

    public TaxCalculation calcTax(final Bet bet, final TaxType taxType, final TaxCalcType calcType, final BigDecimal tax) {
        final var taxCalculationBaseAmount = getTaxCalculationBaseAmount(bet, taxType);
        final var taxAmount = calcTaxAmount(calcType, tax, taxCalculationBaseAmount);

        final var betOutcome = bet.betOutcome();
        return new TaxCalculation(
                betOutcome,
                betOutcome,
                betOutcome.subtract(taxAmount),
                getTaxRate(betOutcome, taxAmount),
                taxAmount
        );
    }

    private BigDecimal calcTaxAmount(final TaxCalcType calcType, final BigDecimal tax, final BigDecimal taxCalculationBaseAmount) {
        switch (calcType) {
            case PER_AMOUNT -> {
                return tax;
            }
            case PER_RATE -> {
                return taxCalculationBaseAmount.multiply(tax);
            }
            default -> throw new IllegalArgumentException("Unknow tax calculation type " + calcType);
        }
    }

    private BigDecimal getTaxCalculationBaseAmount(final Bet bet, final TaxType taxType) {
        switch (taxType) {
            case GENERAL -> {
                return bet.betOutcome();
            }
            case WINNINGS -> {
                return bet.winnings();
            }
            default -> throw new IllegalArgumentException("Unknown tax type " + taxType);
        }
    }

    private BigDecimal getTaxRate(final BigDecimal betOutcome, final BigDecimal taxAmount) {
        if (betOutcome.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Bet outcome cannot be zero or negative");
        }
        return taxAmount.divide(betOutcome, 4, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
    }
}
