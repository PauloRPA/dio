package claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques;

/**
 * AtaquePesado
 */
public class AtaquePesado implements Ataque{

    @Override
    public String atacar() {
        return "Realizando ataque pesado!!!";
    }

    
}
