package claro_java_springboot.lab_project.design_patterns.aula.facade;

import claro_java_springboot.lab_project.design_patterns.aula.facade.servicos.CepApi;
import claro_java_springboot.lab_project.design_patterns.aula.facade.servicos.CrmService;

/**
 * Facade
 */
public class Facade {

    public void migrarCliente(String nome, String cep) {
        String estado = CepApi.getInstance().getEstado(cep);
        String cidade = CepApi.getInstance().getCidade(cep);

        CrmService.gravarCliente(nome, cep, cidade, estado);
    }

}
