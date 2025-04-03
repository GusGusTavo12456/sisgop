package pe.gob.mininter.sisgop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.gob.mininter.sisgop.model.Alerta;
import pe.gob.mininter.sisgop.service.AlertaService;
import pe.gob.mininter.sisgop.util.UbigeoUtil;

@Controller
public class DashboardController {

    private final AlertaService alertaService;
    private final UbigeoUtil ubigeoUtil;

    @Autowired
    public DashboardController(AlertaService alertaService, UbigeoUtil ubigeoUtil) {
        this.alertaService = alertaService;
        this.ubigeoUtil = ubigeoUtil;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String departamento,
            @RequestParam(required = false) String provincia,
            @RequestParam(required = false) String distrito,
            Pageable pageable,
            Model model
    ) {
        // Consultar alertas con filtros y paginación
        Page<Alerta> pagina = alertaService.buscarAlertas(buscar, departamento, provincia, distrito, pageable);

        // Convertir códigos a nombres usando UbigeoUtil
        pagina.getContent().forEach(alerta -> {
            alerta.setDepartamento(ubigeoUtil.getNombreDepartamento(alerta.getDepartamento()));
            alerta.setProvincia(ubigeoUtil.getNombreProvincia(alerta.getProvincia()));
            alerta.setDistrito(ubigeoUtil.getNombreDistrito(alerta.getDistrito()));
        });

        // Agregar al modelo
        model.addAttribute("pagina", pagina);
        model.addAttribute("buscar", buscar);
        model.addAttribute("departamento", departamento);
        model.addAttribute("provincia", provincia);
        model.addAttribute("distrito", distrito);

        return "dashboard";
    }
}
