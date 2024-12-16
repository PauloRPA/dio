package claro_java_springboot.lab_project.design_patterns.aula.singleton;

/**
 * SingletonEager
 */
public class SingletonEager {
    
    private static SingletonEager instance = new SingletonEager();

    private SingletonEager() {
        super();
    }

    public static SingletonEager get() {
        return instance;
    }

}
