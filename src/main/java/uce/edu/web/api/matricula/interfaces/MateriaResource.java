package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
@Produces(MediaType.APPLICATION_JSON)
public class MateriaResource {

   @Inject
   private MateriaService materiaService;

   // 1.
   @GET
   @Path("")
   public Response listarTodos() {
      return Response.ok(materiaService.listarTodos()).build();
   }

   // 2.
   @GET
   @Path("/{id}")
   public Response consultarPorId(@PathParam("id") Integer id) {
      return Response.ok(materiaService.consultarPorId(id)).build();
   }

   // 3.
   @POST
   @Path("")
   public Response guardar(Materia materia) {
      materiaService.crear(materia);
      return Response.status(Response.Status.CREATED).entity(materia).build();
   }

   // 4.
   @PUT
   @Path("/{id}")
   public Response actualizar(@PathParam("id") Integer id, Materia materia) {
      materiaService.actualizar(id, materia);
      return Response.status(209).entity(null).build();
   }

   // 5.
   @PATCH
   @Path("/{id}")
   public Response actualizarParcial(@PathParam("id") Integer id, Materia materia) {
      materiaService.actualizarParcial(id, materia);
      return Response.status(209).entity(null).build();
   }

   // 6.
   @DELETE
   @Path("/{id}")
   public Response borrar(@PathParam("id") Integer id) {
      materiaService.eliminar(id);
      return Response.noContent().build();
   }

   // Nuevos endpoints
   // 7.
   @GET
   @Path("/{codigo}")
   public Response buscarPorCodigo(@PathParam("codigo") String codigo) {
      return Response.ok(materiaService.buscarPorCodigo(codigo)).build();
   }

   // 8.
   @GET
   @Path("/{semestre}")
   public Response buscarPorSemestre(@PathParam("semestre") String semestre) {
      return Response.ok(materiaService.buscarPorSemestre(semestre)).build();
   }
}
