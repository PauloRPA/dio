package claro_java_springboot.lab_project.design_patterns.aula.singleton;

/**
 * SingletonLazyHolder
 */
public class SingletonLazyHolder {

    private static class InstanceHolder {
        private static SingletonLazyHolder instance = new SingletonLazyHolder();
    }

    private SingletonLazyHolder() {
        super();
    }

    public static SingletonLazyHolder get() {
        return InstanceHolder.instance;
    }
}
