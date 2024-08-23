package claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp;

public abstract class Conteudo {

    protected String titulo;
    protected String descricao;
    protected Boolean isConcluido;
    protected Integer cargaHoraria;
    protected Long experiencia;

    public Conteudo(String titulo, String descricao, Integer cargaHoraria, Long experiencia) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.experiencia = experiencia;
        this.isConcluido = false;
    }

    public abstract boolean assistir();

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean isConcluido() {
        return isConcluido;
    }

    public Long getExperiencia() {
        return experiencia;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    protected void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    protected void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    protected void setIsConcluido(Boolean isConcluido) {
        this.isConcluido = isConcluido;
    }

    protected void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

}
