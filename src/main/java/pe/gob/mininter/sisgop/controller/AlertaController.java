package pe.gob.mininter.sisgop.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.gob.mininter.sisgop.model.Alerta;
import pe.gob.mininter.sisgop.service.AlertaService;

@Controller
@RequestMapping("/alerta")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/form")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alerta", new Alerta());
        return "alerta-form";
    }

    @PostMapping("/guardar")
    public String guardarAlerta(@ModelAttribute @Valid Alerta alerta, BindingResult result) {
        if (result.hasErrors()) {
            return "alerta-form";
        }
        alertaService.guardarAlerta(alerta);
        return "redirect:/dashboard";
    }

    @GetMapping("/editar/{id}")
    public String editarAlerta(@PathVariable Long id, Model model) {
        Alerta alerta = alertaService.obtenerPorId(id);
        if (alerta == null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("alerta", alerta);
        return "alerta-form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlerta(@PathVariable Long id) {
        alertaService.eliminarPorId(id); // corregido aquí
        return "redirect:/dashboard";
    }
}
