package si.gaspervrhovsek.tributa;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/tributa")
public class TributaResource {
    @GET
    public String hello() {
        return "Hello, Tributa!";
    }
}
