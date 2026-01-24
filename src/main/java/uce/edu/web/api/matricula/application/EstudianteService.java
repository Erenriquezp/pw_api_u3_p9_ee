package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {

    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> estudiantes = new ArrayList<>();
        for (Estudiante est :  this.estudianteRepository.listAll()) {
            estudiantes.add(this.mapper(est));
        }
        return estudiantes;
    }

    public  EstudianteRepresentation consultarPorId(Integer id) {
        return this.mapper(this.estudianteRepository.findById(id.longValue()));
    }

    @Transactional
    public void crear(EstudianteRepresentation estuR) {
        this.estudianteRepository.persist(this.mapperToEstudiante(estuR));
    }

    @Transactional
    public void actualizar(Integer id, EstudianteRepresentation est) {
        Estudiante estu = this.mapperToEstudiante(est);
        estu.apellido = est.apellido;
        estu.nombre = est.nombre;
        estu.fechaNacimiento = est.fechaNacimiento;
        // Se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Integer id, EstudianteRepresentation est) {
        Estudiante estu = this.mapperToEstudiante(est);
        if (est.nombre != null) {
            estu.nombre = est.nombre;
        }
        if (est.apellido != null) {
            estu.apellido = est.apellido;
        }
        if (est.fechaNacimiento != null) {
            estu.fechaNacimiento = est.fechaNacimiento;   
        }
        // Se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void eliminar(Integer id) {
        this.estudianteRepository.deleteById(id.longValue());
    }

    public List<Estudiante> buscarPorProvincia(String provincia, String genero) {
        //return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();

    }

    private EstudianteRepresentation mapper(Estudiante est) {
        EstudianteRepresentation estuR = new EstudianteRepresentation();
        estuR.id = est.id.intValue();
        estuR.nombre = est.nombre;
        estuR.apellido = est.apellido;
        estuR.fechaNacimiento = est.fechaNacimiento;
        estuR.provincia = est.provincia;
        estuR.genero = est.genero;
        return estuR;
    }

    private Estudiante mapperToEstudiante(EstudianteRepresentation estuR) {
        Estudiante est = new Estudiante();
        est.id = estuR.id;
        est.nombre = estuR.nombre;
        est.apellido = estuR.apellido;
        est.fechaNacimiento = estuR.fechaNacimiento;
        est.provincia = estuR.provincia;
        est.genero = estuR.genero;
        return est;
    }
}
