package si.gaspervrhovsek.tributa;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.UUID;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import si.gaspervrhovsek.tributa.model.rest.TaxationRequest;
import si.gaspervrhovsek.tributa.model.rest.TaxationResponse;

@QuarkusTest
class TributaResourceTest {

    @Test
    void calcTax() {

        final var taxationResultResponse = given()
                                                   .header("Content-Type", "application/json")
                                                   .auth()
                                                   .oauth2(GenerateToken.generateOauth2(GenerateToken.TRADER_A))
                                                   .body(new TaxationRequest(UUID.fromString("b6a14eda-a6dd-461a-bbbc-cd5d27646881"), new BigDecimal(700), new BigDecimal("1.5")))
                                                   .when()
                                                   .post("/tributa")
                                                   .then()
                                                   .statusCode(200)
                                                   .extract()
                                                   .as(TaxationResponse.class);

        final var expectedResponse = new TaxationResponse(
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0")
        );
        MatcherAssert.assertThat(taxationResultResponse, is(expectedResponse));
    }
}