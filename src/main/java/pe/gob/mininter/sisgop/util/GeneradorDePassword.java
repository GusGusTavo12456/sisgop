package pe.gob.mininter.sisgop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneradorDePassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("🕊️ Contraseña en texto plano: " + rawPassword);
        System.out.println("🕊️ Contraseña encriptada: " + encodedPassword);
    }
}
