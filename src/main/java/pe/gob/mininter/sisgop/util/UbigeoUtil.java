package pe.gob.mininter.sisgop.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UbigeoUtil {

    private final Map<String, String> departamentos = new HashMap<>();
    private final Map<String, String> provincias = new HashMap<>();
    private final Map<String, String> distritos = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            cargarUbigeo("/static/data/departamentos.json", mapper, departamentos);
            cargarUbigeo("/static/data/provincias.json", mapper, provincias);
            cargarUbigeo("/static/data/distritos.json", mapper, distritos);

            System.out.println("✅ Datos de ubigeo cargados correctamente para la gloria de Dios.");
        } catch (Exception e) {
            System.err.println("❌ Error cargando archivos de ubigeo:");
            e.printStackTrace();
        }
    }

    private void cargarUbigeo(String path, ObjectMapper mapper, Map<String, String> destino) throws Exception {
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null) {
            throw new IllegalStateException("Archivo no encontrado: " + path);
        }

        List<Map<String, String>> lista = mapper.readValue(stream, new TypeReference<>() {});
        for (Map<String, String> item : lista) {
            destino.put(item.get("id"), item.get("name"));
        }
    }

    public String getNombreDepartamento(String id) {
        return departamentos.getOrDefault(id, id);
    }

    public String getNombreProvincia(String id) {
        return provincias.getOrDefault(id, id);
    }

    public String getNombreDistrito(String id) {
        return distritos.getOrDefault(id, id);
    }
}
