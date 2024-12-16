package claro_java_springboot.lab_project.bootcamp_com_oo;

import claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp.Conteudo;

public class Mentoria extends Conteudo {

    public Mentoria(String titulo, String descricao, Integer cargaHoraria, Long experiencia) {
        super(titulo, descricao, cargaHoraria, experiencia);
    }

    @Override
    public boolean assistir() {
        setIsConcluido(true);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Mentoria '%s', %s", getTitulo(), getDescricao());
    }

}
