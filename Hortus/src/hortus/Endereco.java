package hortus;

public class Endereco {
	private int idEndereco;
	private int idUsuario;	
	
	private String endRua;
	private String endNum;
	private String endComplemento;
	private String endBairro;
	private String endCEP;
	private String endCidade;
	private String endEstado;	
	
	public Endereco(String endRua, String endNum, String endComplemento, String endBairro, String endCEP, String endCidade, String endEstado) {
		this.setIdUsuario(-1);
		this.setEndRua(endRua);
		this.setEndNum(endNum);
		this.setEndBairro(endBairro);
		this.setEndCEP(endCEP);
		this.setEndCidade(endCidade);
		this.setEndEstado(endEstado);
		this.setEndComplemento(endComplemento);
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getEndRua() {
		return endRua;
	}

	public void setEndRua(String endRua) {
		this.endRua = endRua;
	}

	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
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
	
	public String getEndComplemento(){
		return endComplemento;
	}
	
	public void setEndComplemento(String endComplemento){
		this.endComplemento = endComplemento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
