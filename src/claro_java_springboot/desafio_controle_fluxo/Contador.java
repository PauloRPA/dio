import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta classe foi criada á pedido do Lab Project
 * chamado 'Criando Um Pequeno Sistema Para Validação de Processo Seletivo'
 * dentro da categoria 'Sintaxe básica com Java'
 *
 * Descrição do desafio.
 * https://github.com/digitalinnovationone/trilha-java-basico/tree/main/desafios/controle-fluxo
 */
public class Contador {

    public static void main(String[] args) {

        try (final Scanner scan = new Scanner(System.in)) {
            System.out.println("Informe o primeiro valor:");
            final int parametro1 = scan.nextInt();
            System.out.println("Informe o segundo valor:");
            final int parametro2 = scan.nextInt();

            contar(parametro1, parametro2);

        } catch (InputMismatchException e) {
            System.out.println("Insira apenas numeros.");
        } catch (ParametrosInvalidosException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void contar(int parametro1, int parametro2) throws ParametrosInvalidosException {
        if (parametro1 > parametro2)
            throw new ParametrosInvalidosException("O segundo parâmetro deve ser maior que o primeiro");
        final int contarAte = parametro2 - parametro1;

        for (int i = 0; i < contarAte; i++) {
            System.out.printf("[%d] ", i);
        }
        System.out.println();
    }

}
