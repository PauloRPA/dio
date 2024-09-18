package claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques;

/**
 * AtaqueNormal
 */
public class AtaqueNormal implements Ataque{

    @Override
    public String atacar() {
        return "Realizando ataque normal";
    }

    
}
