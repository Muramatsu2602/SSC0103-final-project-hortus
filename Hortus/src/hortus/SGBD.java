package hortus;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SGBD {
 	
 	/*
 		CREATE TABLE consumidor(
			 ID				INT			PRIMARY KEY AUTOINCREMENT,
			 NOME			CHAR(50)	NOT NULL,
			 EMAIL			CHAR(50)	NOT NULL UNIQUE,
			 SENHA			CHAR(50)	NOT NULL
			 CPF			CHAR(11)	NOT NULL UNIQUE,
			 TELEFONE		CHAR(15)	NOT NULL,
			 ID_ENDERECO	INT			NOT NULL,
		 );
		 
		 CREATE TABLE produtor(
			ID				INT			PRIMARY KEY AUTOINCREMENT,
			NOME			CHAR(50)	NOT NULL,
			EMAIL			CHAR(50)	NOT NULL UNIQUE,
			SENHA			CHAR(50)	NOT NULL,
			CNPJ			CHAR(14)	NOT NULL,
			TELEFONE		CHAR(15)	NOT NULL,
			ID_ENDERECO		INT			NOT NULL,
			CCIR			CHAR(30)	NOT NULL UNIQUE,
			TIPO_PROD		INT			NOT NULL,
			DESCRICAO		CHAR(200)
		 );
		 
		 CREATE TABLE produto(
			ID_PRODUTO		INT				NOT NULL,
			ID_PRODUTOR		INT				NOT NULL,
			NOME			CHAR(50)		NOT NULL,
			DESCRICAO		CHAR(200),
			QUANTIDADE		NUMERIC(8,2)	NOT NULL,		
			PRECO			NUMERIC(8,2)	NOT NULL,
			UNIDADE			INT				NOT NULL,
			INGREDIENTES	CHAR(100),
			ORGANICO		INT				NOT NULL
		 );
		 
		 CREATE TABLE compra(
		 	ID				INT 			PRIMARY KEY AUTOINCREMENT,
		 	ID_CONSUMIDOR	INT				NOT NULL,
		 	VALOR_FINAL		NUMERIC(8,2)	NOT NULL,
		 	ID_ENDERECO		INT				NOT NULL,	
		 	DESCRICAO		CHAR(200)
		 );
		 
		 CREATE TABLE itens_compra (
			ID				INT 		PRIMARY KEY AUTOINCREMENT,
			ID_COMPRA		INT			NOT NULL,
			ID_PRODUTO		INT			NOT NULL,
			QTD				DECIMAL(10,2)  NOT NULL
		 );
		 
		 CREATE TABLE endereco(
			ID				INT 		PRIMARY KEY AUTOINCREMENT,
			RUA				CHAR(50)	NOT NULL,
			NUMERO			CHAR(10)	NOT NULL,
			COMPLEMENTO		CHAR(30),
			BAIRRO			CHAR(20)	NOT NULL,
			CEP 			CHAR(10)	NOT NULL,
			CIDADE			CHAR(30)	NOT NULL,
			ESTADO			CHAR(20)	NOT NULL
		 );
 	*/
 	
 	public Connection connect(){
        Connection con = null;
        try{
            String url = "jdbc:sqlite:sqlite/estoque.db";
            con = DriverManager.getConnection(url);
            
        } catch(SQLException e){
            System.out.println("erro"+e.getMessage());
            //JOptionPane.showMessageDialog(null, "Erro \n"+e.getMessage());
        }
        
        return con;
    }
 	
}
