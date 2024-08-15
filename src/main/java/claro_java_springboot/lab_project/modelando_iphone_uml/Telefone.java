package claro_java_springboot.lab_project.modelando_iphone_uml;

/**
 * Telefone
 */
public class Telefone extends Aplicativo {

    public Telefone() {
        super("Telefone");
    }

    public void ligar() {
        System.out.println("Ligando...");
    }

    public void atender() {
        System.out.println("Atendendo...");
    }

    public void iniciarCorreioVoz() {
        System.out.println("Iniciando correio de voz...");
    }

}
