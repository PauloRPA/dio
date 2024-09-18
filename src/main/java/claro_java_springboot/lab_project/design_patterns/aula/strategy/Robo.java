package claro_java_springboot.lab_project.design_patterns.aula.strategy;

import claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques.Ataque;

/**
 * Robo
 */
public class Robo {

    private Ataque ataque;

    public Robo(Ataque ataque) {
        this.ataque = ataque;
    }

    public String atacar() {
        return this.ataque.atacar();
    }

    public Ataque getAtaque() {
        return ataque;
    }

    public void setAtaque(Ataque ataque) {
        this.ataque = ataque;
    }
}
