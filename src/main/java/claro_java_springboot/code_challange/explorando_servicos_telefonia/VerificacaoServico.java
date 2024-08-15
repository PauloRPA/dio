package claro_java_springboot.code_challange.explorando_servicos_telefonia;

import java.util.Scanner;

/**
 * Esta classe foi criada á pedido do Code challange
 * chamado 'Explorando Serviços de Telefonia'
 * dentro da categoria 'Sintaxe básica com Java'
 */
public class VerificacaoServico {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada do serviço a ser verificado
        String servico = scanner.nextLine().trim();

        // Entrada do nome do cliente e os serviços contratados
        String entradaCliente = scanner.nextLine().trim();

        // Separando o nome do cliente e os serviços contratados
        String[] partes = entradaCliente.split(",");
        String nomeCliente = partes[0];
        boolean contratado = false;

        for (int i = 1; i < partes.length; i++) {
            contratado |= partes[i].equals(servico);
            if (contratado)
                break;
        }
        System.out.println(contratado ? "Sim" : "Nao");

        scanner.close();
    }

    public static class Parte2 {

        // Função para verificar se o cliente contratou um combo completo
        public static String verificarComboCompleto(String[] servicosContratados) {
            // Variáveis booleanas para verificar a contratação de cada serviço
            boolean movelContratado = false;
            boolean bandaLargaContratada = false;
            boolean tvContratada = false;

            // TODO: Itere sobre os serviços contratados
            for (String servico : servicosContratados) {
                switch (servico) {

                    case "movel":
                        movelContratado = true;
                        break;

                    case "banda larga":
                        bandaLargaContratada = true;
                        break;

                    case "tv":
                        tvContratada = true;
                        break;

                    default:
                        break;
                }
            }

            // TODO: Verifique se todos os serviços foram contratados
            if (movelContratado && bandaLargaContratada && tvContratada) {
                return "Combo Completo";
            } else {
                return "Combo Incompleto";
            }
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Solicitando ao usuário a lista de serviços contratados
            String input = scanner.nextLine();

            // Convertendo a entrada em uma lista de strings
            String[] servicosContratados = input.split(",");

            // Verificando se o cliente contratou um combo completo
            String resultado = verificarComboCompleto(servicosContratados);

            // Exibindo o resultado
            System.out.println(resultado);

            // Fechando o scanner
            scanner.close();
        }
    }
}
