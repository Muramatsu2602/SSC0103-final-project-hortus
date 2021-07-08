

public abstract class Usuario {
	// Informações básicas
	private String nome;
	private String CPF;
	private String telefone;

	// Endereço
	private String endRua;
	private int endNum;
	private String endBairro;
	private String endCEP;
	private String endCidade;
	private String endEstado;
	
	// Informações de login
	private int id;
	private String email;
	private String senha;
	
	public Usuario()
	{
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndRua() {
		return endRua;
	}
	
	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}
	
	public int getEndNum() {
		return endNum;
	}
	
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	
	public String getEndBairro() {
		return endBairro;
	}
	
	public void setEndBairro(String endBairro) {
		this.endBairro = endBairro;
	}
	
	public String getEndCEP() {
		return endCEP;
	}
	
	public void setEndCEP(String endCEP) {
		this.endCEP = endCEP;
	}
	
	public String getEndCidade() {
		return endCidade;
	}
	
	public void setEndCidade(String endCidade) {
		this.endCidade = endCidade;
	}
	
	public String getEndEstado() {
		return endEstado;
	}
	
	public void setEndEstado(String endEstado) {
		this.endEstado = endEstado;
	}
	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	private void setEmail(String email) {
		this.email = email;
	}
	
	private void setSenha(String senha) {
		this.senha = senha;
	}
}
