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
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materia")
public class MateriaResource {

   @Inject
   private MateriaService materiaService;

   // 1.
   @GET
   @Path("")
   public List<Materia> listarTodos() {
      return materiaService.listarTodos();
   }

   // 2.
   @GET
   @Path("/{id}")
   public Materia consultarPorId(@PathParam("id") Integer id) {
      return materiaService.consultarPorId(id);
   }

   // 3.
   @POST
   @Path("")
   public void guardar(Materia materia) {
      materiaService.crear(materia);
   }

   // 4.
   @PUT
   @Path("/{id}")
   public void actualizar(@PathParam("id") Integer id, Materia materia) {
      materiaService.actualizar(id, materia);
   }

   // 5.
   @PATCH
   @Path("/{id}")
   public void actualizarParcial(@PathParam("id") Integer id, Materia materia) {
      materiaService.actualizarParcial(id, materia);
   }

   // 6.
   @DELETE
   @Path("/{id}")
   public void borrar(@PathParam("id") Integer id) {
      materiaService.eliminar(id);
   }

   // Nuevos endpoints
   // 7.
   @GET
   @Path("/{codigo}")
   public Materia buscarPorCodigo(@PathParam("codigo") String codigo) {
      return materiaService.buscarPorCodigo(codigo);
   }

   // 8.
   @GET
   @Path("/{semestre}")
   public List<Materia> buscarPorSemestre(@PathParam("semestre") String semestre) {
      return materiaService.buscarPorSemestre(semestre);
   }
}
