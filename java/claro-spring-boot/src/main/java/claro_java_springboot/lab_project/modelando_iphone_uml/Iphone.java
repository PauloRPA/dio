package claro_java_springboot.lab_project.modelando_iphone_uml;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Iphone
 */
public class Iphone {

    Set<Aplicativo> aplicativos;

    public Iphone() {
        aplicativos = new HashSet<>();
    }

    public void adicionarAplicativo(Aplicativo app) {
        aplicativos.add(app);
    }

    public void removerAplicativo(Aplicativo app) {
        if (aplicativos.contains(app)) {
            aplicativos.remove(app);
        }
    }

    public Set<Aplicativo> listarAplicativos() {
        return new HashSet<>(Collections.unmodifiableSet(aplicativos));
    }

}
