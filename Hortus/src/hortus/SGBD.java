package hortus;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SGBD {
 	
 	/*
 		CREATE TABLE consumidor(
			 ID				 INTEGER			 PRIMARY KEY AUTOINCREMENT,
			 NOME			 CHAR(50)	 NOT NULL,
			 EMAIL			 CHAR(50)	 NOT NULL  UNIQUE,
			 SENHA			 CHAR(50)	 NOT NULL,
			 CPF			 CHAR(11)	 NOT NULL  UNIQUE,
			 TELEFONE		 CHAR(15)	 NOT NULL,
			 ID_ENDERECO	 INTEGER			 NOT NULL
		 );
		 
		 CREATE TABLE produtor(
			ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			NOME			CHAR(50)	NOT NULL,
			EMAIL			CHAR(50)	NOT NULL UNIQUE,
			SENHA			CHAR(50)	NOT NULL,
			CNPJ			CHAR(14)	NOT NULL,
			TELEFONE		CHAR(15)	NOT NULL,
			ID_ENDERECO		INTEGER		NOT NULL,
			CCIR			CHAR(30)	NOT NULL UNIQUE,
			TIPO_PROD		INTEGER		NOT NULL,
			DESCRICAO		CHAR(200)
		 );
		 
		 CREATE TABLE produto(
			ID_PRODUTO		INTEGER			NOT NULL,
			ID_PRODUTOR		INTEGER			NOT NULL,
			NOME			CHAR(50)		NOT NULL UNIQUE,
			DESCRICAO		CHAR(200),
			QUANTIDADE		NUMERIC(8,2)	NOT NULL,		
			PRECO			NUMERIC(8,2)	NOT NULL,
			UNIDADE			INTEGER			NOT NULL,
			INGREDIENTES	CHAR(100),
			ORGANICO		INTEGER			NOT NULL
		 );
		 
		 CREATE TABLE compra(
		 	ID				INTEGER			PRIMARY KEY AUTOINCREMENT,
		 	ID_CONSUMIDOR	INTEGER			NOT NULL,
		 	VALOR_FINAL		NUMERIC(8,2)	NOT NULL,
		 	ID_ENDERECO		INTEGER			NOT NULL,	
		 	DESCRICAO		CHAR(200)
		 );
		 
		 CREATE TABLE itens_compra (
			ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			ID_COMPRA		INTEGER		NOT NULL,
			ID_PRODUTO		INTEGER		NOT NULL,
			QTD				DECIMAL(10,2)  NOT NULL
		 );
		 
		 CREATE TABLE endereco(
			ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			RUA				CHAR(50)	NOT NULL,
			NUMERO			CHAR(10)	NOT NULL,
			COMPLEMENTO		CHAR(30),
			BAIRRO			CHAR(20)	NOT NULL,
			CEP 			CHAR(10)	NOT NULL,
			CIDADE			CHAR(30)	NOT NULL,
			ESTADO			CHAR(20)	NOT NULL
		 );
		 
		 CREATE TABLE produto_favorito(
			 ID				INTEGER		PRIMARY KEY AUTOINCREMENT,
			 ID_CONSUMIDOR	INTEGER		NOT NULL,
			 ID_PRODUTO		INTEGER		NOT NULL
		 );
 	*/
 	
 	public Connection connect(){
        Connection con = null;
        String banco = "Hortus.db";
        try{
            String url = "jdbc:sqlite:../db/"+banco;
            con = DriverManager.getConnection(url);
            
        } catch(SQLException e){
            System.out.println("erro"+e.getMessage());
            //JOptionPane.showMessageDialog(null, "Erro \n"+e.getMessage());
        }
        
        return con;
    }
    
    public void insereConsumidor(Consumidor cons)
    {
		Connection con = this.connect();
		
		try{
			String sql = "SELECT last_insert_rowid() FROM consumidor";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("ID");
			cons.setId(id);
			
			sql = "INSERT INTO consumidor (NOME, EMAIL, SENHA, CPF, TELEFONE , ID_ENDERECO) VALUES (?, ?, ?, ?, ?, ?, ?);";
			
			stmt.setString(1, cons.getNome());
			stmt.setString(2, cons.getEmail());
			stmt.setString(3, cons.getSenha());
			stmt.setString(4, cons.getCpf());
			stmt.setString(5, cons.getTelefone());
			stmt.setInt(6, cons.getEndereco().getIdEndereco());
			
			stmt.execute();
			con.close();
		} catch(SQLException e)
		{
			System.out.println("erro"+e.getMessage());
		}
	}
	
	public void insereProdutor(Produtor produtor) {
		try {
			Connection con = this.connect();
			
			String sql = "SELECT last_insert_rowid() FROM produtor";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("ID");
			produtor.setId(id);
			
			sql = "INSERT INTO produtor(nome, cpf, telefone, id_endereco, email, senha, ccir, tipo_prod, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
			stmt = con.prepareStatement(sql);
			stmt.setString(1, produtor.getNome());
			stmt.setString(2, produtor.getCpf());
			stmt.setString(3, produtor.getTelefone());
			stmt.setInt(4, produtor.getEndereco().getIdEndereco());
			stmt.setString(5, produtor.getCcir());
			stmt.setInt(6, produtor.getTipoProd());
			stmt.setString(7, produtor.getDescricao());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}
	
	public void insereProduto(Produto prod) {
		try{
			Connection con = connect();
			
			String sql = "SELECT last_insert_rowid() FROM produto";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("ID");
			prod.setIdProduto(id);
		
			sql = "INSERT INTO produto (ID_PRODUTOR, NOME, DESCRICAO, QUANTIDADE, PRECO, UNIDADE, INGREDIENTES, ORGANICO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";		   
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, prod.getIdProdutor());
			pstmt.setString(2, prod.getNomeProduto());
			pstmt.setString(3, prod.getDescricao());
			pstmt.setDouble(4, prod.getQuantidade());
			pstmt.setDouble(5, prod.getPrecoProduto());
			pstmt.setInt(6, prod.getUnidade());
			pstmt.setString(7, prod.getIngredientes());
			pstmt.setBoolean(8, prod.isOrganico());
			
			
			pstmt.execute();
			con.close();
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
 
 	public void insereCompra(Compra compra) {
		try {
			Connection con = this.connect();
			
			String sql = "SELECT last_insert_rowid() FROM compra";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("ID");
			compra.setIdCompra(id);
			
			sql = "INSERT INTO compra(ID_CONSUMIDOR, VALOR_FINAL, ID_ENDERECO, DESCRICAO) VALUES (?, ?, ?, ?);";
		
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, compra.getConsumidor().getId());
			stmt.setDouble(2, compra.getValorFinal());
			stmt.setInt(3, compra.getEndereco().getIdEndereco());
			stmt.setString(4, compra.getDescricao());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}	
 	
 	public void insereItensCompra(Compra compra, Produto prod, Double qtd) {
		try {
			Connection con = this.connect();

			String sql = "INSERT INTO itens_compra(ID_COMPRA, ID_PRODUTO, QTD) VALUES (?, ?, ?);";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, compra.getIdCompra());
			stmt.setInt(2, prod.getIdProduto());
			stmt.setDouble(3, qtd);
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}	
 	
 	public void insereEndereco(Endereco endereco) {
		try {
			Connection con = this.connect();
			
			String sql = "SELECT last_insert_rowid() FROM compra";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt("ID");
			endereco.setIdEndereco(id);
			
			sql = "INSERT INTO endereco(RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, CIDADE, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
			stmt = con.prepareStatement(sql);
			stmt.setString(1, endereco.getEndRua());
			stmt.setString(2, endereco.getEndNum());
			stmt.setString(3, endereco.getEndComplemento());
			stmt.setString(4, endereco.getEndBairro());
			stmt.setString(5, endereco.getEndCEP());
			stmt.setString(6, endereco.getEndCidade());
			stmt.setString(7, endereco.getEndEstado());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}	
 	
 	public void insereProdutoFavorito(Consumidor consumidor, Produto prod) {
		try {
			Connection con = this.connect();

			String sql = "INSERT INTO produto_favorito(ID_CONSUMIDOR, ID_PRODUTO) VALUES (?, ?);";
		
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, consumidor.getId());
			stmt.setInt(2, prod.getIdProduto());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
	}
 	
 	public Consumidor loginConsumidor(String email, String senha) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM consumidor WHERE email = '?' AND senha = '?';";
			PreparedStatement stmt = con.prepareStatement(sql);
 			stmt.setString(1, email);
 			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = '?';";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery(sql2);
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o consumidor e no fim retorná-lo
				Consumidor cons = new Consumidor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"));
				
				con.close();
				return cons;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Produtor loginProdutor(String email, String senha) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM produtor WHERE email = '?' AND senha = '?';";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(1, senha);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = '?';";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery(sql2);
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o Produtor e no fim retorná-lo
				Produtor prodt = new Produtor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"), rs.getString("CCIR"), rs.getInt("TIPO_PROD"), rs.getString("DESCRICAO"));
				
				con.close();
				return prodt;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
			
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Consumidor getConsumidorById(int id) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM consumidor WHERE id = '?';";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = '?';";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery(sql2);
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o consumidor e no fim retorná-lo
				Consumidor cons = new Consumidor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"));
				
				con.close();
				return cons;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
			
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
 	
 	public Produtor getProdutorById(int id) throws HortusException
 	{
 		try {
			Connection con = this.connect();
 			String sql = "SELECT * FROM produtor WHERE ID = '?';";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
			{
				Endereco end = null;
				String sql2 = "SELECT * FROM endereco WHERE ID = '?';";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				
				stmt.setInt(1, rs.getInt("ID_ENDERECO"));
				ResultSet rs2 = stmt2.executeQuery(sql2);
				if(rs2.next())
				{
					end = new Endereco(rs2.getString("RUA"), rs2.getString("NUMERO"), rs2.getString("COMPLEMENTO"), rs2.getString("BAIRRO"), rs2.getString("CEP"), rs2.getString("CIDADE"), rs2.getString("ESTADO"));
				}
				
				// Inicializar o Produtor e no fim retorná-lo
				Produtor prodt = new Produtor(rs.getInt("ID"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("TELEFONE"), end, rs.getString("EMAIL"), rs.getString("SENHA"), rs.getString("CCIR"), rs.getInt("TIPO_PROD"), rs.getString("DESCRICAO"));
				
				con.close();
				return prodt;
			} else {
				con.close();
				throw new HortusException("Usuario e/ou Senha incorretos"); 	
			}
			
 		} catch(SQLException e)
 		{
 			System.out.println("erro"+e.getMessage());
 		}
 		return null;
 	}
}

/*
	Operações de inserção
	
	Consumidor:
		- Pegar o proximo valor de ID
		String sql = "SELECT last_insert_rowid() FROM consumidor";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuerry(sql);
		rs.next();
		int id = rs.getInt("ID");		
		
	
		- String sql = "INSERT INTO consumidor (ID, NOME, EMAIL, SENHA, CPF, TELEFONE , ID_ENDERECO) VALUES
					   (?, ?, ?, ?, ?, ?, ?);"			   
		try{
			Connection conn = connect("consumidor.db");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, nome);
			pstmt.setString(3, email);
			pstmt.setString(4, senha);
			pstmt.setString(5, cpf);
			pstmt.setString(6, telefone);
			pstmt.setInt(7, endereco);
			
			pstmt.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		- String sql = "SELECT * WHERE email = '?' AND senha = '?'"
		ResultSet rs;
		
		try{
			Connection conn = connect("consumidor.db");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, senha);
			
			rs = pstmt.executeQuerry();
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
		// Agora, pegar os dados que retornaram da querry e colocar no objeto de consumidor atual	
	
	Produtor:
	- Pegar o proximo valor de ID
	
		Connection con = this.connect();
		
		String sql = "SELECT last_insert_rowid() FROM produtor";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuerry(sql);
		rs.next();
		int id = rs.getInt("ID");
		
		String sql = "INSERT INTO produtor(nome, cpf, telefone, id_endereco, email, senha, ccir, tipo_prod, descricao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, produtor.getNome());
			stmt.setString(2, produtor.getCpf());
			stmt.setString(3, produtor.getTelefone());
			stmt.setInt(4, produtor.getEndereco().getId());
			stmt.setString(5, produtor.getCcir());
			stmt.setInt(6, produtor.getTipoProd());
			stmt.setString(7, produtor.getDescricao());
			
			stmt.execute();
			con.close();
		} catch(SQLException e){
            System.out.println("erro"+e.getMessage());
        }
		
		
	
	Produto:
	- Pegar o proximo valor de ID
		String sql = "SELECT last_insert_rowid() FROM produto";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuerry(sql);
		rs.next();
		int id = rs.getInt("ID");
	
		- String sql = "INSERT INTO produto (ID_PRODUTOR, NOME, DESCRICAO, QUANTIDADE, PRECO, UNIDADE, INGREDIENTES, ORGANICO) VALUES
					   (?, ?, ?, ?, ?, ?, ?);"			   
		try{
			Connection conn = connect("consumidor.db");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prod.getIdProdutor);
			pstmt.setString(2, nome);
			pstmt.setString(3, descricao);
			pstmt.setString(4, prod.getQuantidade);
			pstmt.setString(5, prod.getPrecoProduto);
			pstmt.setString(6, prod.getUnidade);
			pstmt.setString(6, prod.getIngredientes);
			pstmt.setString(6, prod.getUnidade);
			pstmt.setInt(7, prod);
			
			pstmt.executeUpdate();
		}catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		
	
	Compra:
	- Pegar o proximo valor de ID
		String sql = "SELECT last_insert_rowid() FROM compra";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuerry(sql);
		rs.next();
		int id = rs.getInt("ID");
	
	
	
	Itens Compra:
	- Pegar o proximo valor de ID
		String sql = "SELECT last_insert_rowid() FROM itens_compra";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuerry(sql);
		rs.next();
		int id = rs.getInt("ID");
	
	
	
	Endereço:
	- Pegar o proximo valor de ID
		String sql = "SELECT last_insert_rowid() FROM endereco";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuerry(sql);
		rs.next();
		int id = rs.getInt("ID");
*/
