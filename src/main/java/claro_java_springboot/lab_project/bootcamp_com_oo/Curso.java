package claro_java_springboot.lab_project.bootcamp_com_oo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp.Conteudo;

public class Curso extends Conteudo {

    private Queue<String> aulas;

    public Curso(String titulo, String descricao, Integer cargaHoraria, Long experiencia, List<String> aulas) {
        super(titulo, descricao, cargaHoraria, experiencia);
        this.aulas = new LinkedList<>(aulas);
    }

    @Override
    public boolean assistir() {
        if (aulas.isEmpty()) {
            return false;
        }
        aulas.poll();

        if (aulas.isEmpty()) {
            setIsConcluido(true);
        }
        return true;
    }

    public String getAulaAtual() {
        return this.aulas.peek();
    }

    @Override
    public String toString() {
        return String.format("Curso '%s', %s", getTitulo(), getAulaAtual());
    }
}
