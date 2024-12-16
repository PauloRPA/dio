package claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Bootcamp
 */
public class Bootcamp {

    private final String nome;
    private final String descricao;
    private final LocalDate inicio;
    private final LocalDate termino;
    private final Queue<Conteudo> conteudos;
    private final Set<Usuario> inscritos;

    public Bootcamp(String nome, String descricao, LocalDate inicio, LocalDate termino) {
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
        this.termino = termino;
        this.conteudos = new LinkedList<>();
        this.inscritos = new HashSet<>();
    }

    public Inscricao inscrever(Usuario usuario) {
        if (usuario.getNascimento().until(now(), YEARS) < 18)
            throw new IllegalArgumentException("O Usuario deve ter ao menos 18 anos.");
        if (getInscritos().contains(usuario))
            throw new IllegalArgumentException("Usuario ja escrito neste bootcamp.");
        if (now().isAfter(getTermino()))
            throw new IllegalArgumentException("O Usuario nao pode se inscrever em um bootcamp que ja terminou.");

        this.inscritos.add(usuario);
        return new Inscricao(this, usuario);
    }

    public void adicionarConteudo(Conteudo conteudo) {
        this.conteudos.add(conteudo);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public Queue<Conteudo> getConteudos() {
        return new LinkedList<>(this.conteudos);
    }

    public Set<Usuario> getInscritos() {
        return Collections.unmodifiableSet(this.inscritos);
    }

}
