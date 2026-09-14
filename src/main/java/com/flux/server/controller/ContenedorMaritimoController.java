package com.flux.server.controller;

import com.flux.server.entity.ContenedorMaritimo;
import com.flux.server.repository.ContenedorMaritimoRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;
import jcf.systemRole;

@ApplicationScoped
@Path("/plugin/demo/contenedormaritimo")
@RolesAllowed({systemRole.ADMIN})
@Tag(name = "Contenedor maritimo", description = "API for Library management")
public class ContenedorMaritimoController {

    @Inject
    ContenedorMaritimoRepository contenedirMaritimoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "findAll", description = "Returns all records")
    public List<ContenedorMaritimo> findAll() {
        return contenedirMaritimoRepository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "save", description = "Saves a new ContenedorMaritimo")
    public Response save(ContenedorMaritimo contenedirMaritimo) {
        contenedirMaritimoRepository.save(contenedirMaritimo);
        return Response.ok("{\"message\": \"Saved successfully\"}").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "update", description = "Updates an existing ContenedorMaritimo")
    public Response update(ContenedorMaritimo contenedirMaritimo) {
        contenedirMaritimoRepository.save(contenedirMaritimo);
        return Response.ok("{\"message\": \"Updated successfully\"}").build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "delete", description = "Deletes a ContenedorMaritimo by id")
    public Response delete(@PathParam("id") String id) {
        contenedirMaritimoRepository.delete(id);
        return Response.ok("{\"message\": \"Deleted successfully\"}").build();
    }
}
