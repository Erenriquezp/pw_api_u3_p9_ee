package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.matricula.infraestructure.HijoRepository;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.domain.Hijo;

@ApplicationScoped
public class HijoService {
    
    @Inject
    private HijoRepository hijoRepository;

    public List<HijoRepresentation> buscarPorIdEstudiante(Integer idEstudiante) {

        List<HijoRepresentation> lista = new ArrayList<>();
        for (Hijo h: this.hijoRepository.buscarPorIdEstudiante(idEstudiante)) {
            lista.add(this.mapperToHijoR(h));
        }
        return lista;
    }

    private HijoRepresentation mapperToHijoR(Hijo hijo) {
        HijoRepresentation hijoR = new HijoRepresentation();
        hijoR.id = hijo.id.intValue();
        hijoR.nombre = hijo.nombre;
        hijoR.apellido = hijo.apellido;
        return hijoR;
    }
}
