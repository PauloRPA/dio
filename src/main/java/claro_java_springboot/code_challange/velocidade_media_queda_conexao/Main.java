package claro_java_springboot.code_challange.velocidade_media_queda_conexao;

public class Main {

    // Função para calcular a velocidade média de conexão de internet
    public static double calcularVelocidadeMedia(String[] velocidades) {
        int total = 0;
        for (String velocidade : velocidades) {
            total += Integer.parseInt(velocidade);
        }
        return total / velocidades.length;
    }

    public static String verificarQuedaConexao(String[] velocidades) {
        for (String velocidade : velocidades) {
            if (velocidade.equals("0")) {
                return "Queda de Conexao";
            }
        }
        return "Sem Quedas";
    }

    public static void mediaVelocidadeTest() {
        System.out.printf("Input: {130,134,132,130,130,136} Expected: %2$s Actual: %1$s\n",
                calcularVelocidadeMedia(new String[] { "130", "134", "132", "130", "130", "136" }),
                "132");
        System.out.printf("Input: {120,130,125,125,120,130} Expected: %2$s Actual: %1$s\n",
                calcularVelocidadeMedia(new String[] { "120", "130", "125", "125", "120", "130" }),
                "125");
        System.out.printf("Input: {110,100,120,110,115,105} Expected: %2$s Actual: %1$s\n",
                calcularVelocidadeMedia(new String[] { "110", "100", "120", "110", "115", "105" }),
                "110");
    }

    public static void main(String[] args) {
        mediaVelocidadeTest();
        System.out.println();
        quedaTest();
    }

    private static void quedaTest() {
        System.out.printf("Input: {130,50,0,90,130,136} Expected: %2$s Actual: %1$s\n",
                verificarQuedaConexao(new String[] { "130", "50", "0", "90", "130", "136" }),
                "Queda de Conexao");
        System.out.printf("Input: {120,130,125,125,120,130} Expected: %2$s Actual: %1$s\n",
                verificarQuedaConexao(new String[] { "120", "130", "125", "125", "120", "130" }),
                "Sem Quedas");
        System.out.printf("Input: {110,125,80,0,105,125} Expected: %2$s Actual: %1$s\n",
                verificarQuedaConexao(new String[] { "110", "125", "80", "0", "105", "125" }),
                "Queda de Conexao");
    }
}
