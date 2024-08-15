package claro_java_springboot.lab_project.modelando_iphone_uml.musicas;

import claro_java_springboot.lab_project.modelando_iphone_uml.Aplicativo;

/**
 * MusicPlayer
 */
public abstract class MusicPlayer extends Aplicativo {

    public MusicPlayer(String nome) {
        super(nome);
    }

    public abstract void tocar();

    public abstract void pausar();

    public abstract void selecionarMusica();
}
