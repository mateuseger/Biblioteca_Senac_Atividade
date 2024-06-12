package controller;

import database.Mysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.mAutores;
import model.mEditoras;

public class cAutores {
    public void cadastrar(mAutores modelE) {
        Connection conn = Mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO autores (nome,endereco,numero,bairro,cidade,cpf) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, modelE.getNome());
            stmt.setString(2, modelE.getEndereco());
            stmt.setString(3, modelE.getNumero());
            stmt.setString(4, modelE.getBairro());
            stmt.setString(5, modelE.getCidade());
            stmt.setString(6, modelE.getCpf());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<mAutores> listar() {
        Connection conn = Mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<mAutores> lista = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM autores");
            rs = stmt.executeQuery();
            while (rs.next()) {
                mAutores modelA = new mAutores();
                modelA.setId_autor(rs.getInt("id_autor"));
                modelA.setNome(rs.getString("nome"));
                modelA.setEndereco(rs.getString("endereco"));
                modelA.setNumero(rs.getString("numero"));
                modelA.setBairro(rs.getString("bairro"));
                modelA.setCidade(rs.getString("cidade"));
                modelA.setCpf(rs.getString("cpf"));

                lista.add(modelA);

            }
        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public List<mAutores> pesquisar(String texto, int filtro) {
        Connection conn = Mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<mAutores> lista = new ArrayList<>();

        try {

            if (filtro == 0) {
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE id_autor = ?");
                    stmt.setString(1, texto);
                    rs = stmt.executeQuery();
            } 
            
            else {
                    stmt = conn.prepareStatement("SELECT * FROM autores WHERE nome LIKE ?");
                    stmt.setString(1, "%" + texto + "%");
                    rs = stmt.executeQuery();
            }
            
            while (rs.next()) {
                mAutores modelA = new mAutores();
                modelA.setId_autor(rs.getInt("id_autor"));
                modelA.setNome(rs.getString("nome"));
                modelA.setEndereco(rs.getString("endereco"));
                modelA.setNumero(rs.getString("numero"));
                modelA.setBairro(rs.getString("bairro"));
                modelA.setCidade(rs.getString("cidade"));
                modelA.setCpf(rs.getString("cpf"));

                lista.add(modelA);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public void alterar(mAutores modelA) {
        Connection conn = Mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("UPDATE autores SET nome = ?, endereco = ?, numero = ?, bairro = ?, cidade = ?, cpf = ? WHERE id_autor = ? ");
            stmt.setString(1, modelA.getNome());
            stmt.setString(2, modelA.getEndereco());
            stmt.setString(3, modelA.getNumero());
            stmt.setString(4, modelA.getBairro());
            stmt.setString(5, modelA.getCidade());
            stmt.setString(6, modelA.getCpf());
            stmt.setInt(7, modelA.getId_autor());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor alterado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(mAutores modelA) {
        Connection conn = Mysql.conexao();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM autores WHERE id_autor = ? ");
            stmt.setInt(1, modelA.getId_autor());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Autor excluído com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(cEditoras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
