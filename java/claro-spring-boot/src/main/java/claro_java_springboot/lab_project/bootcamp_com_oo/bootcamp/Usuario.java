package claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Usuario {

    private String nome;
    private LocalDate nascimento;
    private Long experiencia;
    private Set<Inscricao> bootcampsConcluidos;

    public Usuario(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.experiencia = 0L;
        bootcampsConcluidos = new HashSet<Inscricao>();
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Long getTotalHorasEstudadas() {
        return this.bootcampsConcluidos.stream()
                .map(Inscricao::getBootcamp)
                .flatMap(bootcamp -> bootcamp.getConteudos().stream())
                .mapToLong(Conteudo::getCargaHoraria)
                .sum();
    }

    public Long getExperiencia() {
        return experiencia;
    }

    public void addExperiencia(Long exp) {
        this.experiencia += exp;
    }

    public void adicionarConclusao(Inscricao conteudo) {
        this.bootcampsConcluidos.add(conteudo);
    }

    public Set<Inscricao> getBootcampsConcluidos() {
        return Collections.unmodifiableSet(getBootcampsConcluidos());
    }

    public List<Conteudo> getTodosConteudosConcluidos() {
        return this.bootcampsConcluidos.stream()
                .map(Inscricao::getBootcamp)
                .flatMap(bootcamp -> bootcamp.getConteudos().stream())
                .toList();
    }

}
