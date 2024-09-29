package si.gaspervrhovsek.tributa;

import java.util.HashSet;
import java.util.List;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

/**
 * A simple utility class to generate and print a JWT token string to stdout.
 */
public class GenerateToken {
    public static final String TRADER_A = "b6a14eda-a6dd-461a-bbbc-cd5d27646881";
    public static final String TRADER_B = "c7b25fdb-b7ee-572b-cccd-de6e38757992";
    public static final String TRADER_C = "d8c36gfc-c8ff-683c-ddee-ef7f49868a03";
    public static final String TRADER_D = "e9d47hgd-d9gg-794d-effe-fg8g50979b14";
    public static final String TRADER_E = "f0e58ihe-e0hh-805e-ffgg-gh9h61080c25";

    /**
     * Generate JWT token
     */
    public static void main(String[] args) {
        String token = Jwt.issuer("https://example.com/issuer")
                               .upn("b6a14eda-a6dd-461a-bbbc-cd5d27646881")
                               .groups(new HashSet<>(List.of("Trader")))
                               .claim(Claims.birthdate.name(), "2001-07-13")
                               .sign();
        System.out.println(token);
    }

    static String generateOauth2(String upn) {
        return Jwt.upn(upn)
                       .issuer("https://example.com/issuer")
                       .groups("Trader")
                       .claim(Claims.birthdate.name(), "2001-07-13")
                       .sign();
    }
}
