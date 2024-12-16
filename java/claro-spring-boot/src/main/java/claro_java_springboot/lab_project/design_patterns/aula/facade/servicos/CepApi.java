package claro_java_springboot.lab_project.design_patterns.aula.facade.servicos;

/**
 * CepApi
 */
public class CepApi {

    private static class CepApiInstanceHolder {
        private static CepApi instance = new CepApi();
    }

    private CepApi() {
        super();
    }

    public static CepApi getInstance() {
        return CepApiInstanceHolder.instance;
    }

    public String getCidade(String cep) {
        return "Sao paulo";
    }

    public String getEstado(String cep) {
        return "SP";
    }

}
