package claro_java_springboot.lab_project.design_patterns.aula.singleton;

/**
 * Singleton
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy() {
        super();
    }

    public static SingletonLazy get() {
        instance = instance == null ? new SingletonLazy() : instance;
        return instance;
    }
}
