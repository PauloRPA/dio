package claro_java_springboot.lab_project.modelando_iphone_uml.navegadores;

import claro_java_springboot.lab_project.modelando_iphone_uml.Aplicativo;

/**
 * Firefox
 */
public class Firefox extends Aplicativo implements Navegador {

    public Firefox() {
        super("Firefox");
    }

    @Override
    public void exibirPagina() {
        System.out.println("Exibindo pagina no Firefox");
    }

    @Override
    public void adicionarNovaAba() {
        System.out.println("Adicionando aba no Firefox");
    }

    @Override
    public void atualizarPagina() {
        System.out.println("Atualizando aba no Firefox");
    }

}
