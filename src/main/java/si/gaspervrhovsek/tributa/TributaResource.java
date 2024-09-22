package si.gaspervrhovsek.tributa;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import si.gaspervrhovsek.tributa.enums.TaxationType;
import si.gaspervrhovsek.tributa.model.rest.TaxationRequest;
import si.gaspervrhovsek.tributa.model.rest.TaxationResultResponse;
import si.gaspervrhovsek.tributa.service.TaxationService;
import si.gaspervrhovsek.tributa.service.TraderService;

@Path("/tributa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TributaResource {
    @Inject
    TaxationService taxationService;

    @Inject
    TraderService traderService;

    @POST
    @Path("/general/{type}")
    public TaxationResultResponse calculateGeneralTaxation(@PathParam("type") TaxationType type, TaxationRequest request) {

    }

    @POST
    @Path("/winnings/{type}")
    public TaxationResultResponse calculateWinningsTaxation(@PathParam("type") TaxationType type, TaxationRequest request) {

    }
}
