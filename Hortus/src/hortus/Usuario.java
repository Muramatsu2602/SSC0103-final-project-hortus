package hortus;

public abstract class Usuario {
	// Informacoes basicas
	private String nome;
	private String cpf;
	private String telefone;

	private Endereco endereco;
	
	// Informacoes de login
	private int id;
	private String email;
	
	private String senha;
	
	public Usuario(int id, String nome, String cpf, String telefone, Endereco endereco, String email, String senha)
	{
		this.setId(id);
		this.setNome(nome);
		this.setCpf(cpf);
		this.setTelefone(telefone);
		this.setEndereco(endereco);
		this.setEmail(email);
		this.setSenha(senha);
	}
	
	public Usuario() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
