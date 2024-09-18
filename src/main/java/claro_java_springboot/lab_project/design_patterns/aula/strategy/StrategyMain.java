package claro_java_springboot.lab_project.design_patterns.aula.strategy;

import claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques.AtaqueNormal;
import claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques.AtaquePesado;
import claro_java_springboot.lab_project.design_patterns.aula.strategy.ataques.AtaqueRapido;

/**
 * StrategyMain
 */
public class StrategyMain {

    public static void main(String[] args) {
        final Robo robo = new Robo(new AtaqueNormal());
        System.out.println(robo.atacar());

        robo.setAtaque(new AtaqueRapido());
        System.out.println(robo.atacar());

        robo.setAtaque(new AtaquePesado());
        System.out.println(robo.atacar());
    }
}
