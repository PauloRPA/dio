package claro_java_springboot.lab_project.banco_digital_eoo.banco;

public interface Politica {

    Transacao getSuportaTransacao();

    double getDescontos(double valorDaTransacao);
}
