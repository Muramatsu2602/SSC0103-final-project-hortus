import io.jsondb.annotation.Id;

public class Endereco {
	@Id
	private int idEndereco;
		
	private String endRua;
	private int endNum;
	private String endBairro;
	private String endCEP;
	private String endCidade;
	private String endEstado;	
	
	public Endereco(String endRua, int endNum, String endBairro, String endCEP, String endCidade, String endEstado) {
		this.setEndRua(endRua);
		this.setEndNum(endNum);
		this.setEndBairro(endBairro);
		this.setEndCEP(endCEP);
		this.setEndCidade(endCidade);
		this.setEndEstado(endEstado);
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
}
