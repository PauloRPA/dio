package claro_java_springboot.lab_project.banco_digital_eoo.banco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {

    private String nome;
    private LocalDate nascimento;

    public Cliente(String nome, LocalDate nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    // GETTERS SETTERS HASHCODE EQUALS

    public String getNome() {
        return nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    @Override
    public String toString() {
        var dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "[Nome=" + nome + ", nascimento=" + dateFormat.format(nascimento) + "]";
    }

}
