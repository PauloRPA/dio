package claro_java_springboot.lab_project.modelando_iphone_uml.musicas;

/**
 * Musicas
 */
public class Musicas extends MusicPlayer {

    public Musicas() {
        super("Musicas");
    }

    @Override
    public void tocar() {
        System.out.println("Tocando musica do armazenamento local");
    }

    @Override
    public void pausar() {
        System.out.println("Pausando musica do armazenamento local");
    }

    @Override
    public void selecionarMusica() {
        System.out.println("Selecionando musica do armazenamento local");
    }
}
