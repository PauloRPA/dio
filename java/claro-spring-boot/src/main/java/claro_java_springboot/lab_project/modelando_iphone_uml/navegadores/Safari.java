package claro_java_springboot.lab_project.modelando_iphone_uml.navegadores;

import claro_java_springboot.lab_project.modelando_iphone_uml.Aplicativo;

/**
 * Safari
 */
public class Safari extends Aplicativo implements Navegador {

    public Safari() {
        super("Safari");
    }

    @Override
    public void exibirPagina() {
        System.out.println("Exibindo pagina no Safari");
    }

    @Override
    public void adicionarNovaAba() {
        System.out.println("Adicionando aba no Safari");
    }

    @Override
    public void atualizarPagina() {
        System.out.println("Atualizando aba no Safari");
    }

}
