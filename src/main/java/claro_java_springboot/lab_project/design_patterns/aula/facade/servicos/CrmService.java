package claro_java_springboot.lab_project.design_patterns.aula.facade.servicos;

/**
 * CrmService
 */
public class CrmService {

    static String esc = "\u001b";
    static String reset = esc + "[0m";
    static String bold = esc + "[1m";
    static String blue = esc + "[34m";

    public static void gravarCliente(String nome, String cep, String cidade, String estado) {
        System.out.printf("Cliente:\n\tNome: %s\n\tCEP: %s\n\tCidade: %s\n\tEstado: %s\n", nome, cep, cidade, estado);
        System.out.println("Cliente salvo no sistema CRM");
    }
}
