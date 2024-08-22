package claro_java_springboot.lab_project.banco_digital_eoo.banco;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Banco {

    private final int agencia;
    private final String nome;
    private final Endereco endereco;
    private final InstituicaoBancaria instituicaoBancaria;
    private final List<Politica> politicasCc;
    private final List<Politica> politicasCp;

    Banco(int agencia, String nome, InstituicaoBancaria instituicaoBancaria, Endereco endereco) {
        this.agencia = agencia;
        this.nome = nome;
        this.instituicaoBancaria = instituicaoBancaria;
        this.endereco = endereco;
        this.politicasCc = new ArrayList<>();
        this.politicasCp = new ArrayList<>();
    }

    public ContaCorrente criarContaCorrente(Cliente cliente) {
        if (cliente.getNascimento().until(LocalDate.now(), ChronoUnit.YEARS) < 18)
            throw new IllegalArgumentException(
                    String.format("O usuario %s deve ter pelo menos dezoito anos.", cliente.getNome()));

        final long randomNumber = new Random().nextLong(10000000, 100000000000L);
        String numeroConta = String.valueOf(instituicaoBancaria.hashCode() + this.hashCode() + randomNumber);

        ContaCorrente novaConta = new ContaCorrente(this, numeroConta, cliente);
        politicasCc.forEach(novaConta::adicionarPolitica);
        return novaConta;
    }

    public ContaPoupanca criarContaPoupanca(Cliente cliente) {
        if (cliente.getNascimento().until(LocalDate.now(), ChronoUnit.YEARS) < 18)
            throw new IllegalArgumentException(
                    String.format("O usuario %s deve ter pelo menos dezoito anos.", cliente.getNome()));

        final long randomNumber = new Random().nextLong(10000000, 100000000000L);
        String numeroConta = String.valueOf(instituicaoBancaria.hashCode() + this.hashCode() + randomNumber);

        ContaPoupanca novaConta = new ContaPoupanca(this, numeroConta, cliente);
        politicasCp.forEach(novaConta::adicionarPolitica);
        return novaConta;
    }

    void adicionarPolicaCc(Politica politica) {
        this.politicasCc.add(politica);
    }

    void adicionarPolicaCp(Politica politica) {
        this.politicasCp.add(politica);
    }

    // GETTERS SETTERS HASHCODE EQUALS

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + agencia;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
        result = prime * result + ((instituicaoBancaria == null) ? 0 : instituicaoBancaria.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Banco other = (Banco) obj;
        if (agencia != other.agencia)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (endereco == null) {
            if (other.endereco != null)
                return false;
        } else if (!endereco.equals(other.endereco))
            return false;
        if (instituicaoBancaria == null) {
            if (other.instituicaoBancaria != null)
                return false;
        } else if (!instituicaoBancaria.equals(other.instituicaoBancaria))
            return false;
        return true;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public InstituicaoBancaria getInstituicaoBancaria() {
        return instituicaoBancaria;
    }

    @Override
    public String toString() {
        return "Banco " + instituicaoBancaria + " [agencia=" + agencia + ", nome=" + nome + ", endereco=" + endereco
                + "]";
    }
}
