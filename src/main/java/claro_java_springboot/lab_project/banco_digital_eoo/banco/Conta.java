package claro_java_springboot.lab_project.banco_digital_eoo.banco;

import static claro_java_springboot.lab_project.banco_digital_eoo.banco.Transacao.DEPOSITO;
import static claro_java_springboot.lab_project.banco_digital_eoo.banco.Transacao.SAQUE;
import static claro_java_springboot.lab_project.banco_digital_eoo.banco.Transacao.TRANSFERENCIA;
import static claro_java_springboot.lab_project.banco_digital_eoo.banco.Transacao.TRANSFERENCIA_OUTRA_INSTITUICAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Conta
 */
public abstract class Conta {

    protected Cliente cliente;
    protected Banco banco;
    protected String numero;
    protected int agencia;
    protected BigDecimal saldoEmCentavos;
    protected List<Politica> politicas;

    protected Conta(Banco banco, String numero, Cliente cliente) {
        this.banco = banco;
        this.numero = numero;
        this.agencia = banco.getAgencia();
        this.saldoEmCentavos = BigDecimal.ZERO;
        this.cliente = cliente;
        this.politicas = new ArrayList<>();
    }

    public void depositar(double quantidadeEmReais) {
        final double totalEmDescontos = calcularDesconto(DEPOSITO, quantidadeEmReais);
        adicionarAConta(quantidadeEmReais - totalEmDescontos);
    }

    public boolean sacar(double quantidadeEmReais) {
        final double totalEmDescontos = calcularDesconto(SAQUE, quantidadeEmReais);
        return subtrairDaConta(quantidadeEmReais + totalEmDescontos);
    }

    private void adicionarAConta(double quantidadeEmReais) {
        saldoEmCentavos = saldoEmCentavos.add(new BigDecimal(quantidadeEmReais * 100));
    }

    private boolean subtrairDaConta(double quantidadeEmReais) {
        BigDecimal quantidadeEmCentavos = new BigDecimal(quantidadeEmReais * 100);
        BigDecimal restante = saldoEmCentavos.subtract(quantidadeEmCentavos);
        if (restante.signum() < 0)
            return false;

        saldoEmCentavos = restante;
        return true;
    }

    public boolean transferir(double quantidadeEmReais, Conta destino) {
        double totalEmDescontos = calcularDesconto(TRANSFERENCIA, quantidadeEmReais);

        String esteBanco = this.getBanco().getInstituicaoBancaria().getNome();
        String bancoDestino = destino.getBanco().getInstituicaoBancaria().getNome();
        if (!bancoDestino.equals(esteBanco)) {
            totalEmDescontos += calcularDesconto(TRANSFERENCIA_OUTRA_INSTITUICAO, quantidadeEmReais);
        }

        if (subtrairDaConta(quantidadeEmReais + totalEmDescontos)) {
            destino.adicionarAConta(quantidadeEmReais);
            return true;
        }

        return false;
    }

    protected double calcularDesconto(Transacao transacao, double quantidadeEmReais) {
        double totalEmDescontos = 0;

        List<Politica> politicasTranferencia = politicas.stream()
                .filter(pol -> pol.getSuportaTransacao().equals(transacao))
                .toList();

        if (!politicasTranferencia.isEmpty()) {
            for (Politica politica : politicas) {
                totalEmDescontos += politica.getDescontos(quantidadeEmReais);
            }
        }

        return totalEmDescontos;
    }

    void adicionarPolitica(Politica politica) {
        this.politicas.add(politica);
    }

    // GETTERS SETTERS HASHCODE EQUALS

    public Banco getBanco() {
        return banco;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldoEmReais() {
        return saldoEmCentavos.divide(new BigDecimal(100)).doubleValue();
    }

    public BigDecimal getSaldoEmCentavos() {
        return saldoEmCentavos;
    }

    public int getAgencia() {
        return agencia;
    }

    @Override
    public String toString() {
        return "Conta [Saldo=" + getSaldoEmReais() + ", numero=" + numero + ", agencia="
                + agencia + " Cliente: " + cliente + " [banco=" + banco + "]";
    }

}
