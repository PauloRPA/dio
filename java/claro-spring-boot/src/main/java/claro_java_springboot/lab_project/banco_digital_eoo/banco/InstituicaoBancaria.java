package claro_java_springboot.lab_project.banco_digital_eoo.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * InstituicaoBancaria
 */
public class InstituicaoBancaria {

    private int numeroUltimaAgencia;
    private String nome;
    private final List<Banco> agencias;
    private final List<Politica> politicasCc;
    private final List<Politica> politicasCp;

    public InstituicaoBancaria(String nome) {
        this.nome = nome;
        this.agencias = new ArrayList<>();
        this.numeroUltimaAgencia = new Random().nextInt(1000, 2000);
        this.politicasCc = new ArrayList<>();
        this.politicasCp = new ArrayList<>();
    }

    public Banco criarAgencia(String nome, Endereco endereco) {
        Banco banco = new Banco(numeroUltimaAgencia++, nome, this, endereco);
        politicasCc.forEach(banco::adicionarPolicaCc);
        politicasCp.forEach(banco::adicionarPolicaCp);
        agencias.add(banco);
        return banco;
    }

    public void adicionarPoliticaCp(Politica politica) {
        politicasCp.add(politica);
        agencias.forEach(agencia -> agencia.adicionarPolicaCp(politica));
    }

    public void adicionarPoliticaCc(Politica politica) {
        politicasCc.add(politica);
        agencias.forEach(agencia -> agencia.adicionarPolicaCc(politica));
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "[nome=" + nome + "]";
    }
}
