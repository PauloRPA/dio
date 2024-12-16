package claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques;

/**
 * AtaqueRapido
 */
public class AtaqueRapido implements Ataque{

    @Override
    public String atacar() {
        return "Realizando ataque veloz!!!";
    }

    
}
