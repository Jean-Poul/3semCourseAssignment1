package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.CarFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

/**
 *
 * @author jplm
 */
// Car REST endpoints
@Path("car")
public class CarResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final CarFacade FACADE = CarFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // count REST endpoint
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarCount() {
        long count = FACADE.getCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    // All REST endpoint
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllCars())).build();
    }

    // maker REST endpoint
    @Path("/{maker}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCarsByMaker(@PathParam("maker") String maker) {
        return Response.ok().entity(GSON.toJson(FACADE.getCarsByMaker(maker))).build();
    }

    // year REST endpoint
    @Path("year/{year}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCarsByYear(@PathParam("year") int year) {
        return Response.ok().entity(GSON.toJson(FACADE.getCarsByYear(year))).build();
    }
}
