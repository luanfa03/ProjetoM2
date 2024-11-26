package model;

// Classe que representa um paciente
public class Paciente {
    // Atributos privados do paciente
    private String cpf;        // CPF do paciente
    private String nome;       // Nome do paciente
    private int idade;         // Idade do paciente
    private String telefone;   // Telefone do paciente
    private String descricao;  // Descrição adicional sobre o paciente

    // Construtor com parâmetros
    public Paciente(String cpf, String nome, int idade, String telefone, String descricao) {
        this.cpf = cpf;                // Inicializa o CPF do paciente
        this.nome = nome;              // Inicializa o nome do paciente
        this.idade = idade;            // Inicializa a idade do paciente
        this.telefone = telefone;      // Inicializa o telefone do paciente
        this.descricao = descricao;    // Inicializa a descrição do paciente
    }

    // Construtor sem parâmetros (construtor padrão)
    public Paciente() {
        // Inicializa valores padrão se necessário (neste caso, nenhum valor é atribuído por padrão)
    }

    // Métodos getters e setters para acessar e modificar os atributos

    // Retorna o CPF do paciente
    public String getCpf() {
        return cpf;
    }

    // Define o CPF do paciente
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Retorna o nome do paciente
    public String getNome() {
        return nome;
    }

    // Define o nome do paciente
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a idade do paciente
    public int getIdade() {
        return idade;
    }

    // Define a idade do paciente
    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Retorna o telefone do paciente
    public String getTelefone() {
        return telefone;
    }

    // Define o telefone do paciente
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Retorna a descrição do paciente
    public String getDescricao() {
        return descricao;
    }

    // Define a descrição do paciente
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
