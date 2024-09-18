package claro_java_springboot.lab_project.design_patterns.aula.singleton;

/**
 * SingletonMain
 */
public class SingletonMain {

    public static void main(String[] args) {

        final boolean isEagerEquals = SingletonEager.get() == SingletonEager.get();
        System.out.println("SingletonEager:");
        System.out.println(isEagerEquals ? "Both references are the same." : "The references differ.");
        System.out.println();

        final boolean isLazyEquals = SingletonLazy.get() == SingletonLazy.get();
        System.out.println("SingletonLazy:");
        System.out.println(isLazyEquals ? "Both references are the same." : "The references differ.");
        System.out.println();

        final boolean isLazyHolderEquals = SingletonLazyHolder.get() == SingletonLazyHolder.get();
        System.out.println("SingletonLazyHolder:");
        System.out.println(isLazyHolderEquals ? "Both references are the same." : "The references differ.");
        System.out.println();

    }
}
