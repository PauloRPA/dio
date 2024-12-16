package claro_java_springboot.lab_project.modelando_iphone_uml.musicas;

/**
 * Spotify
 */
public class Spotify extends MusicPlayer {

    public Spotify() {
        super("Spotify");
    }

    @Override
    public void tocar() {
        System.out.println("Tocando musica dos servidores do spotify");
    }

    @Override
    public void pausar() {
        System.out.println("Pausando musica dos servidores do spotify");
    }

    @Override
    public void selecionarMusica() {
        System.out.println("Selecionando musica dos servidores do spotify");
    }
}
