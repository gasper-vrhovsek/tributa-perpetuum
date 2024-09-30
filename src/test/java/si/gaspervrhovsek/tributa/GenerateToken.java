package si.gaspervrhovsek.tributa;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

/**
 * A simple utility class to generate and print a JWT token string to stdout.
 */
public class GenerateToken {
    public static final String TRADER_A = "b6a14eda-a6dd-461a-bbbc-cd5d27646881";
    public static final String TRADER_B = "c7b25fdb-b7ee-572b-cccd-de6e38757992";
    public static final String TRADER_C = "40a82048-312d-4fba-b7f4-7e9bfb87ff13";
    public static final String TRADER_D = "58099627-14bf-4d39-8ab9-31f5d53a8580";
    public static final String TRADER_E = "50aad14b-9f8c-4241-9bcb-ea1f0849fb1d";

    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        final var traders = List.of(TRADER_A, TRADER_B, TRADER_C, TRADER_D, TRADER_E);
        final var randomTrader = traders.get(new Random().nextInt(traders.size()));

        String token = Jwt.issuer("https://example.com/issuer")
                               .upn(randomTrader)
                               .groups(new HashSet<>(List.of("Trader")))
                               .sign();
        System.out.println(token);
    }

    static String generateOauth2(String traderId) {
        return Jwt.upn(traderId)
                       .issuer("https://example.com/issuer")
                       .groups("Trader")
                       .sign();
    }
}
