package claro_java_springboot.lab_project.banco_digital_eoo.banco;

/**
 * ContaPoupanca
 */
public class ContaPoupanca extends Conta {

    protected ContaPoupanca(Banco banco, String numero, Cliente cliente) {
        super(banco, numero, cliente);
    }

    @Override
    public String toString() {
        return String.format("Conta Poupanca de %s no %s [Saldo: %.2f Numero: %s Agencia: %d]", cliente.getNome(),
                banco.getInstituicaoBancaria().getNome(), getSaldoEmReais(), numero, agencia);
    }
}
