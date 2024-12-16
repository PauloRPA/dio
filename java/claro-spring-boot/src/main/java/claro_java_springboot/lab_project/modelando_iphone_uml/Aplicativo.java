package claro_java_springboot.lab_project.modelando_iphone_uml;

/**
 * Aplicativo
 */
public abstract class Aplicativo {
    private String nome;

    public Aplicativo(String nome) {
        this.nome = nome;
    }

    public void abrir() {
        System.out.printf("Abrindo %s", nome);
    }

    public void fechar() {
        System.out.printf("Fechando %s", nome);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aplicativo other = (Aplicativo) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}
