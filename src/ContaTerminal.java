import java.util.Locale;
import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Por favor, digite o número de sua conta: ");
        final int numero = scan.nextInt();

        System.out.print("Por favor, digite o número da Agência: ");
        String agencia = scan.next();

        System.out.print("Por favor, digite o seu nome: ");
        String nomeCliente = scan.next();

        System.out.print("Por favor, digite o seu saldo: ");
        final double saldo = scan.nextDouble();

        String exemploConcatMetodo = "Olá ".concat(nomeCliente);
        String exemploSinal = ", obrigado por criar uma conta em nosso banco, sua agência é " + agencia + ",";
        String mensagem = String.format("%s%s conta %d e seu saldo %.2f já está disponível para saque.",
                exemploConcatMetodo, exemploSinal, numero, saldo);

        System.out.println(mensagem);

        scan.close();
    }
}
