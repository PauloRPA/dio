package claro_java_springboot.lab_project.design_patterns.aula.facade;

/**
 * FacadeMain
 */
public class FacadeMain {

    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.migrarCliente("Paulo", "21345-12");
    }
}
