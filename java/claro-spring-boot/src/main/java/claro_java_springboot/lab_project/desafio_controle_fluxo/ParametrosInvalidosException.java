package claro_java_springboot.lab_project.desafio_controle_fluxo;

public class ParametrosInvalidosException extends Exception {
    public ParametrosInvalidosException() {
        super();
    }

    public ParametrosInvalidosException(String msg) {
        super(msg);
    }
}
