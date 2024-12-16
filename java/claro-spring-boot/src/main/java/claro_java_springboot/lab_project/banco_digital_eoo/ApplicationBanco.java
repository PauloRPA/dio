package claro_java_springboot.lab_project.banco_digital_eoo;

import java.time.LocalDate;

import claro_java_springboot.lab_project.banco_digital_eoo.banco.Banco;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.Cliente;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.ContaCorrente;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.ContaPoupanca;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.Endereco;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.InstituicaoBancaria;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.Politica;
import claro_java_springboot.lab_project.banco_digital_eoo.banco.Transacao;

/**
 * Este codigo foi feito afim de cumprir com a proposta do lab project
 * "Criando um Banco Digital com Java e Orientação a Objetos". Nao me
 * importei muito em fazer um codigo limpo ou hiper bem organizado,
 * porem acredito que seja o suficiente para demonstrar os possiveis
 * usos da orientacao a objetos. Por favor, ignorar a falta de acentos
 * e cedilhas, o motivo para a falta dos mesmos se da por conta do
 * meu teclado. :)
 */
public class ApplicationBanco {

    private static final String SEPARATOR = "-".repeat(90);

    public static void main(String[] args) {

        InstituicaoBancaria itau = new InstituicaoBancaria("Itau");
        InstituicaoBancaria inter = new InstituicaoBancaria("Inter");
        Endereco santaCruz = new Endereco("Sao paulo", "SP", "Santa cruz", "rua sta. cruz", 2001);
        Endereco pracaArvore = new Endereco("Sao paulo", "SP", "Praca Arvore", "rua da praca", 585);
        Banco interStaCruz = inter.criarAgencia("Santa cruz", santaCruz);
        Banco itauPraca = itau.criarAgencia("Praca da arvore", pracaArvore);

        // Colhe 10% da valor de uma tranferencia feita por um conta CORRENTE a um banco
        // de outra instituicao
        inter.adicionarPoliticaCc(new Politica() {

            @Override
            public Transacao getSuportaTransacao() {
                return Transacao.TRANSFERENCIA_OUTRA_INSTITUICAO;
            }

            @Override
            public double getDescontos(double valorDaTransacao) {
                final long descontos = (long) (valorDaTransacao * 0.1);
                System.out.print("Aplicando taxa de 10% a transacao sendo feita para outra instituicao. ");
                System.out.printf("Valor anterior: %.2f -> Novo valor: %.2f\n", valorDaTransacao,
                        valorDaTransacao + descontos);
                return descontos;
            }

        });

        // Colhe 10% da valor de um saque feito em uma conta POUPANCA
        inter.adicionarPoliticaCp(new Politica() {

            @Override
            public Transacao getSuportaTransacao() {
                return Transacao.SAQUE;
            }

            @Override
            public double getDescontos(double valorDaTransacao) {
                final long descontos = (long) (valorDaTransacao * 0.1);
                System.out.print("Aplicando taxa de 10% ao saque. ");
                System.out.printf("Valor anterior: %.2f -> Novo valor: %.2f\n", valorDaTransacao,
                        valorDaTransacao + descontos);
                return descontos;
            }

        });

        Cliente gustavo = new Cliente("Gustavo", LocalDate.of(1992, 02, 29));
        Cliente ana = new Cliente("Ana", LocalDate.of(1979, 05, 22));

        System.out.println(SEPARATOR);
        System.out.println("Gustavo, dono de uma conta poupanca no Inter saca um valor");
        System.out.println(SEPARATOR);

        ContaPoupanca gustavoInterCp = interStaCruz.criarContaPoupanca(gustavo);
        gustavoInterCp.depositar(10000);
        System.out.println(gustavoInterCp + "\n");
        gustavoInterCp.sacar(500.80);
        System.out.println(gustavoInterCp);
        System.out.println(SEPARATOR);

        System.out.println(SEPARATOR);
        System.out.println("Gustavo, em sua conta corrente no Inter transfere um 1000 a Ana, dona de uma conta Itau");
        System.out.println(SEPARATOR);

        ContaCorrente gustavoInterCc = interStaCruz.criarContaCorrente(gustavo);
        ContaPoupanca anaItauCp = itauPraca.criarContaPoupanca(ana);
        gustavoInterCc.depositar(10000);

        System.out.println(gustavoInterCc);
        System.out.println(anaItauCp + "\n");
        gustavoInterCc.transferir(1000.50, anaItauCp);
        System.out.println(gustavoInterCc);
        System.out.println(anaItauCp);

        System.out.println(SEPARATOR);
    }
}
