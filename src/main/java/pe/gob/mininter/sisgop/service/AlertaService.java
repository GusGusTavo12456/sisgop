package pe.gob.mininter.sisgop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import pe.gob.mininter.sisgop.model.Alerta;
import pe.gob.mininter.sisgop.repository.AlertaRepository;
import pe.gob.mininter.sisgop.util.UbigeoUtil;

import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private UbigeoUtil ubigeoUtil; // ✅ Correctamente inyectado

    public void guardarAlerta(Alerta alerta) {
        alertaRepository.save(alerta);
    }

    public List<Alerta> obtenerAlertas() {
        return alertaRepository.findAll();
    }

    public Alerta obtenerPorId(Long id) {
        return alertaRepository.findById(id).orElse(null);
    }

    public void eliminarPorId(Long id) {
        alertaRepository.deleteById(id);
    }

    public Page<Alerta> buscarAlertas(String buscar, String departamento, String provincia, String distrito, Pageable pageable) {
        List<Alerta> listaFiltrada = alertaRepository.findAll();

        // Convertimos códigos ID a nombre real para compararlos
        if (departamento != null && !departamento.trim().isEmpty()) {
            departamento = ubigeoUtil.getNombreDepartamento(departamento);
        }

        if (provincia != null && !provincia.trim().isEmpty()) {
            provincia = ubigeoUtil.getNombreProvincia(provincia);
        }

        // Filtro por texto libre
        if (buscar != null && !buscar.trim().isEmpty()) {
            String filtro = buscar.trim().toLowerCase();
            listaFiltrada = listaFiltrada.stream()
                    .filter(a ->
                            (a.getTitulo() != null && a.getTitulo().toLowerCase().contains(filtro)) ||
                                    (a.getAcciones() != null && a.getAcciones().toLowerCase().contains(filtro)) ||
                                    (a.getLocalidad() != null && a.getLocalidad().toLowerCase().contains(filtro)))
                    .toList();
        }

        // Filtro por departamento (ya convertido a nombre)
        if (departamento != null && !departamento.trim().isEmpty()) {
            String finalDep = departamento.trim().toLowerCase();
            listaFiltrada = listaFiltrada.stream()
                    .filter(a -> a.getDepartamento() != null && a.getDepartamento().toLowerCase().equals(finalDep))
                    .toList();
        }

        // Filtro por provincia (ya convertido a nombre)
        if (provincia != null && !provincia.trim().isEmpty()) {
            String finalProv = provincia.trim().toLowerCase();
            listaFiltrada = listaFiltrada.stream()
                    .filter(a -> a.getProvincia() != null && a.getProvincia().toLowerCase().equals(finalProv))
                    .toList();
        }

        // Filtro por distrito (comparación directa por nombre)
        if (distrito != null && !distrito.trim().isEmpty()) {
            String finalDis = distrito.trim().toLowerCase();
            listaFiltrada = listaFiltrada.stream()
                    .filter(a -> a.getDistrito() != null && a.getDistrito().toLowerCase().equals(finalDis))
                    .toList();
        }

        // Paginación segura
        int total = listaFiltrada.size();
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), total);
        List<Alerta> pagina = (start < end) ? listaFiltrada.subList(start, end) : List.of();

        return new PageImpl<>(pagina, pageable, total);
    }
}
