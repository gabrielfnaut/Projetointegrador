import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.time.format.DateTimeFormatter;

class Aluno {
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate dataNascimento;

    public Aluno(String nome, String cpf, String endereco, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));   
        }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdade() {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears();
    }

    // Método adicionado
    public String getDataNascimentoFormatada() {
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}

class ListaDeAlunos {
    private ArrayList<Aluno> lista;

    public ListaDeAlunos() {
        lista = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        lista.add(aluno);
    }

    public void mostrarAlunosEmOrdemAlfabetica() {
        Collections.sort(lista, Comparator.comparing(Aluno::getNome));

        for (Aluno aluno : lista) {
            System.out.println("Nome: " + aluno.getNome() + ", Idade: " + aluno.getIdade() + ", Data de Nascimento: " + aluno.getDataNascimentoFormatada());
        }
    }
}

public class SistemaEscolar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaDeAlunos listaDeAlunos = new ListaDeAlunos();

        while (true) {
            System.out.println("\n===== Menu =====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Consultar Alunos em Ordem Alfabética");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n===== Cadastro de Aluno =====");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Data de Nascimento (DD/MM/AAAA): ");
                    String dataNascimento = scanner.nextLine();

                    Aluno aluno = new Aluno(nome, cpf, endereco, dataNascimento);
                    listaDeAlunos.adicionarAluno(aluno);
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n===== Alunos em Ordem Alfabética =====");
                    listaDeAlunos.mostrarAlunosEmOrdemAlfabetica();
                    break;

                case 8:
                    System.out.println("Saindo do Sistema...");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}