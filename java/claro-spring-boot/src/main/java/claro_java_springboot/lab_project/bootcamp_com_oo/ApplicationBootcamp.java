package claro_java_springboot.lab_project.bootcamp_com_oo;

import static java.time.LocalDate.now;

import java.time.LocalDate;
import java.util.List;

import claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp.Bootcamp;
import claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp.Conteudo;
import claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp.Inscricao;
import claro_java_springboot.lab_project.bootcamp_com_oo.bootcamp.Usuario;

/**
 * ApplicationBootcamp
 *
 * Este codigo foi feito afim de cumprir com a proposta do lab project
 * "Abstraindo um Bootcamp Usando Orientação a Objetos em Java".
 *
 * Para começar crie um Usuario, Bootcamp e o conteudo para o bootcamp
 * (Curso ou Mentoria) usando seus construtores normalmente, depois,
 * adicione os conteúdos aos bootcamps e inscreva os usuários
 * no bootcamp. A inscrição de um usuário é como uma matrícula essencialmente,
 * e representa a relação entre o usuário e o bootcamp, incluindo o progresso
 * do usuário pelo curso,data de início e termino do curso e sua conclusão.
 * Para receber uma atividade deve-se pedi-la para o objeto de Inscricao
 * que guarda o progresso do usuário pelo curso.
 * Sempre que uma atividade 'concluida' é entregue o usuário progride no curso
 * e ao seu fim a experiência é contabilizada para aquele usuário.
 */
public class ApplicationBootcamp {

    public static void main(String[] args) {
        Bootcamp claro = new Bootcamp("Claro java springboot", "Java e springboot", now(), LocalDate.of(2040, 03, 29));
        Bootcamp spring = new Bootcamp("Spring specialist", "Conheca tudo sobre Spring!", now(),
                LocalDate.of(2030, 03, 29));

        List<String> aulasClaro = List.of("Conhecendo git", "Comandos basicos", "Comandos intermediarios",
                "Comandos plumbing", "Github");
        List<String> aulasSpring = List.of("Spring framework", "Spring Data", "Spring Security",
                "Spring Batch");

        spring.adicionarConteudo(new Curso("Spring", "Aprenda tudo sobre Spring!", 30, 200L, aulasSpring));
        claro.adicionarConteudo(new Curso("Git", "Sistema de versionamento GIT", 10, 50L, aulasClaro));
        claro.adicionarConteudo(new Mentoria("Futuro das IAs", "A nova era da IA", 2, 100L));

        Usuario usuario = new Usuario("Paulo", LocalDate.of(1996, 06, 29));
        Inscricao inscricaoClaro = claro.inscrever(usuario);
        Inscricao inscricaoSpring = spring.inscrever(usuario);

        cursar(usuario, inscricaoClaro);
        cursar(usuario, inscricaoSpring);

        System.out.printf("O usuario %s tem um total de %d horas estudadas e %d de XP\n", usuario.getNome(),
                usuario.getTotalHorasEstudadas(), usuario.getExperiencia());

        var nomeConteudosConcluidos = usuario.getTodosConteudosConcluidos().stream()
                .map(Conteudo::getTitulo)
                .toList();

        System.out.printf("O usuario concluiu os seguintes cursos: %s\n", nomeConteudosConcluidos);
        System.out.println("-".repeat(80));
    }

    private static void cursar(Usuario usuario, Inscricao inscricao) {
        while (inscricao.hasConteudo()) {
            Conteudo conteudoAtual = inscricao.getConteudoAtual().get();

            while (!conteudoAtual.isConcluido()) {
                System.out.printf("%s esta assistindo a: %s\n", usuario.getNome(), conteudoAtual);
                conteudoAtual.assistir();
            }

            final boolean entrega = inscricao.entregarConteudo(conteudoAtual);
            if (entrega) {
                System.out.printf("\t%s entregou %s e ganhou %d XP\n\n", usuario.getNome(),
                        conteudoAtual.getTitulo(),
                        conteudoAtual.getExperiencia());
            }
        }
    }
}
