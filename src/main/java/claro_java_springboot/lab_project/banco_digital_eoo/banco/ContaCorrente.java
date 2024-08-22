package claro_java_springboot.lab_project.banco_digital_eoo.banco;

/**
 * ContaCorrente
 */
public class ContaCorrente extends Conta {

    protected ContaCorrente(Banco banco, String numero, Cliente cliente) {
        super(banco, numero, cliente);
    }

    @Override
    public String toString() {
        return String.format("Conta Corrente de %s no %s [Saldo: %.2f Numero: %s Agencia: %d]", cliente.getNome(),
                banco.getInstituicaoBancaria().getNome(), getSaldoEmReais(), numero, agencia);
    }

}
