package claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Inscricao {

    private final Usuario usuario;
    private final Bootcamp bootcamp;
    private final Queue<Conteudo> conteudos;
    private final LocalDate dataInscricao;
    private LocalDate dataConclusaoCurso;
    private Boolean isConcluido;

    Inscricao(Bootcamp bootcamp, Usuario usuario) {
        this.bootcamp = bootcamp;
        this.usuario = usuario;
        this.conteudos = bootcamp.getConteudos();
        this.dataInscricao = LocalDate.now();
        this.dataConclusaoCurso = null;
        this.isConcluido = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Bootcamp getBootcamp() {
        return bootcamp;
    }

    public List<Conteudo> getConteudos() {
        return new LinkedList<Conteudo>(conteudos);
    }

    public boolean entregarConteudo(Conteudo conteudo) {
        if (!conteudo.isConcluido || this.conteudos.isEmpty()) {
            return false;
        }

        Conteudo feito = this.conteudos.poll();
        getUsuario().addExperiencia(feito.getExperiencia());

        if (this.conteudos.isEmpty()) {
            setDataConclusaoCurso(now());
            setIsConcluido(true);
            getUsuario().adicionarConclusao(this);
        }

        return true;
    }

    public boolean hasConteudo() {
        return !conteudos.isEmpty();
    }

    public Optional<Conteudo> getConteudoAtual() {
        return Optional.ofNullable(conteudos.peek());
    }

    public LocalDate getDataInscricao() {
        return dataInscricao;
    }

    public Optional<LocalDate> getDataConclusaoCurso() {
        return Optional.ofNullable(dataConclusaoCurso);
    }

    public Boolean getIsConcluido() {
        return isConcluido;
    }

    public void setIsConcluido(Boolean isConcluido) {
        this.isConcluido = isConcluido;
    }

    public void setDataConclusaoCurso(LocalDate dataConclusaoCurso) {
        this.dataConclusaoCurso = dataConclusaoCurso;
    }

}
