package claro_java_springboot.lab_project.modelando_iphone_uml;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import claro_java_springboot.lab_project.modelando_iphone_uml.musicas.Musicas;
import claro_java_springboot.lab_project.modelando_iphone_uml.musicas.Spotify;
import claro_java_springboot.lab_project.modelando_iphone_uml.navegadores.Firefox;
import claro_java_springboot.lab_project.modelando_iphone_uml.navegadores.Safari;

/**
 * Main
 */
public class Application {

    private static final String SEPARATOR = "-".repeat(30);
    private static final Set<String> EXIT_SEQUENCES = Set.of("q", "quit", "exit", "cancel");
    private static final Map<String, Class<?>> APLICATIVOS_DISPONIVEIS = Map.of(
            "Firefox", Firefox.class,
            "Safari", Safari.class,
            "Spotify", Spotify.class,
            "Musicas", Musicas.class,
            "Telefone", Telefone.class);

    public static void main(String[] args) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final Iphone iphone = new Iphone();
        final Scanner scan = new Scanner(System.in);

        String line = "";
        while (!EXIT_SEQUENCES.contains(line)) {
            if (!line.isBlank() && APLICATIVOS_DISPONIVEIS.containsKey(line)) {
                final Aplicativo novoApp = (Aplicativo) APLICATIVOS_DISPONIVEIS.get(line).getDeclaredConstructor()
                        .newInstance();
                iphone.adicionarAplicativo(novoApp);
            }

            if (!line.isBlank() && line.startsWith("-") && APLICATIVOS_DISPONIVEIS.containsKey(line.substring(1))) {
                String appNameToRemove = line.substring(1);
                Iterator<Aplicativo> appIterator = iphone.listarAplicativos().iterator();

                while (appIterator.hasNext()) {
                    final Aplicativo app = appIterator.next();
                    if (app.getNome().equals(appNameToRemove)) {
                        iphone.removerAplicativo(app);
                    }
                }
            }

            printInstrucoes();
            printIphone(iphone);
            line = scan.nextLine();
        }

        scan.close();
    }

    private static void printIphone(Iphone iphone) {

        String instalados = iphone.listarAplicativos().stream()
                .map(Aplicativo::getNome)
                .collect(Collectors.joining(","));

        System.out.printf("Seu aparelho tem um total de %d aplicativos instalados.\n",
                iphone.listarAplicativos().size());
        System.out.printf(
                "Seus aplicativos instalados atualmente são: %s \n",
                instalados);

        System.out.println("Para sair escreva qualquer uma das opções a seguir: q,quit,exit,cancel");
        System.out.println(SEPARATOR);
    }

    private static void printInstrucoes() {
        String storeAppList = APLICATIVOS_DISPONIVEIS.keySet().stream().collect(Collectors.joining(","));
        System.out.println(SEPARATOR);
        System.out.printf(
                "Para instalar um aplicativo basta digitar seu nome, os aplicativos disponíveis são: %s \n",
                storeAppList);
        System.out.printf(
                "Para remover um aplicativo basta digitar seu nome com um menos no começo ex: -Firefox ou -Spotify\n",
                storeAppList);
        System.out.println(SEPARATOR);

    }
}
