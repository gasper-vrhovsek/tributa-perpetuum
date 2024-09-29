package si.gaspervrhovsek.tributa;

import java.util.UUID;

import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import si.gaspervrhovsek.tributa.mapper.TaxCalculationMapper;
import si.gaspervrhovsek.tributa.model.rest.TaxationRequest;
import si.gaspervrhovsek.tributa.model.rest.TaxationResponse;
import si.gaspervrhovsek.tributa.service.TaxationService;

@Path("/tributa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TributaResource {
    @Inject
    JsonWebToken jwt;

    @Inject
    TaxationService taxationService;

    @Inject
    TaxCalculationMapper mapper;

    @POST
    public TaxationResponse calcTax(TaxationRequest request) {
        final var traderId = jwt.getName();
        return mapper.toResponse(taxationService.getTax(UUID.fromString(traderId), request.playedAmount(), request.odd()));
    }
}
