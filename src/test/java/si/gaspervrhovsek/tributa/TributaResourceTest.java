package si.gaspervrhovsek.tributa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.quarkus.test.junit.QuarkusTest;
import si.gaspervrhovsek.tributa.model.rest.TaxationRequest;
import si.gaspervrhovsek.tributa.model.rest.TaxationResponse;

@QuarkusTest
class TributaResourceTest {


    @ParameterizedTest
    @MethodSource("taxCalculationArguments")
    void calcTax(UUID traderId, BigDecimal playedAmount, BigDecimal odd, TaxationResponse expectedResponse) {

        final var taxationResultResponse = given()
                                                   .header("Content-Type", "application/json")
                                                   .auth()
                                                   .oauth2(GenerateToken.generateOauth2(traderId.toString()))
                                                   .body(new TaxationRequest(traderId, playedAmount, odd))
                                                   .when()
                                                   .post("/tributa")
                                                   .then()
                                                   .statusCode(200)
                                                   .extract()
                                                   .as(TaxationResponse.class);
        MatcherAssert.assertThat(taxationResultResponse, is(expectedResponse));
    }

    private static Stream<Arguments> taxCalculationArguments() {
        return Stream.of(
                Arguments.of(UUID.fromString(GenerateToken.TRADER_A), new BigDecimal(700), new BigDecimal("1.5"),
                        new TaxationResponse(new BigDecimal("1050.0000"), new BigDecimal("1050.0000"), new BigDecimal("1015.0000"), new BigDecimal("3.3300"), new BigDecimal("35.0000"))),
                Arguments.of(UUID.fromString(GenerateToken.TRADER_B), new BigDecimal(700), new BigDecimal("1.5"),
                        new TaxationResponse(new BigDecimal("1050.0000"), new BigDecimal("1050.0000"), new BigDecimal("1048.0000"), new BigDecimal("0.1900"), new BigDecimal("2.0000"))),
                Arguments.of(UUID.fromString(GenerateToken.TRADER_C), new BigDecimal(700), new BigDecimal("1.5"),
                        new TaxationResponse(new BigDecimal("1050.0000"), new BigDecimal("1050.0000"), new BigDecimal("1015.0000"), new BigDecimal("3.3300"), new BigDecimal("35.0000"))),
                Arguments.of(UUID.fromString(GenerateToken.TRADER_D), new BigDecimal(700), new BigDecimal("1.5"),
                        new TaxationResponse(new BigDecimal("1050.0000"), new BigDecimal("1050.0000"), new BigDecimal("735.0000"), new BigDecimal("30.0000"), new BigDecimal("315.0000"))),
                Arguments.of(UUID.fromString(GenerateToken.TRADER_D), new BigDecimal(700), new BigDecimal("1.1"),
                        new TaxationResponse(new BigDecimal("770.0000"), new BigDecimal("770.0000"), new BigDecimal("539.0000"), new BigDecimal("30.0000"), new BigDecimal("231.0000"))),
                Arguments.of(UUID.fromString(GenerateToken.TRADER_E), new BigDecimal(700), new BigDecimal("1.5"),
                        new TaxationResponse(new BigDecimal("1050.0000"), new BigDecimal("1050.0000"), new BigDecimal("1046.0000"), new BigDecimal("0.3800"), new BigDecimal("4.0000")))
        );
    }
}