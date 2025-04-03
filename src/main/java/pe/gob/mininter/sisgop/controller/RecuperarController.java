package pe.gob.mininter.sisgop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.gob.mininter.sisgop.model.Usuario;
import pe.gob.mininter.sisgop.repository.UsuarioRepository;

@Controller
public class RecuperarController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/recuperar")
    public String mostrarFormulario() {
        return "recuperar-password";
    }

    @PostMapping("/recuperar")
    public String procesarRecuperacion(
            @RequestParam String username,
            @RequestParam String nuevaPassword,
            @RequestParam String confirmarPassword,
            Model model
    ) {
        if (!nuevaPassword.equals(confirmarPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "recuperar-password";
        }

        Usuario usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario == null) {
            model.addAttribute("error", "Usuario no encontrado.");
            return "recuperar-password";
        }

        // Encriptar y guardar nueva contraseña
        usuario.setPassword(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);

        model.addAttribute("mensaje", "Contraseña actualizada con éxito.");
        return "recuperar-password";
    }
}
