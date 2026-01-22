package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.*;;


@ApplicationScoped
public class MateriaService {
   
   @Inject
   private MateriaRepository materiaRepository;

   // 1. Listar todos
   public List<Materia> listarTodos() {
      return materiaRepository.listAll();
   }

   // 2. Consultar por ID
   public Materia consultarPorId(Integer id) {
      return materiaRepository.findById(id.longValue());
   }

   // 3. Crear
   @Transactional
   public void crear(Materia materia) {
      materiaRepository.persist(materia);
   }

   // 4. Actualizar completo
   @Transactional
   public void actualizar(Integer id, Materia mat) {
      Materia materiaExistente = consultarPorId(id);
      if (materiaExistente != null) {
         materiaExistente.nombre = mat.nombre;
         materiaExistente.codigo = mat.codigo;
         ;materiaExistente.creditos = mat.creditos;
         materiaExistente.semestre = mat.semestre;
         
      }
   }

   // 5. Actualizar parcial
   @Transactional
   public void actualizarParcial(Integer id, Materia mat) {
      Materia materiaExistente = consultarPorId(id);
      if (materiaExistente != null) {
         if (mat.codigo != null) {
            materiaExistente.nombre = mat.nombre;
         }
         if (mat.nombre != null) {
            materiaExistente.codigo = mat.codigo;            
         }
         if (mat.creditos != null) {
            materiaExistente.creditos = mat.creditos;            
         }
         if (mat.semestre != null) {
            materiaExistente.semestre = mat.semestre;
         }
         
      }
   }

   // 6. Eliminar
   @Transactional
   public void eliminar(Integer id) {
      materiaRepository.deleteById(id.longValue());
   }

   // Metodos adicionales
   
   // 7. Buscar por codigo de materia
   public Materia buscarPorCodigo(String codigo) {
      return materiaRepository.find("codigo", codigo).firstResult();
   }

   // 8. Buscar por Semestre
   public List<Materia> buscarPorSemestre(String semestre) {
      return materiaRepository.find("semestre", semestre).list();
   }
}
