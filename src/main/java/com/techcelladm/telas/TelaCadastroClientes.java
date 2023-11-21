package com.techcelladm.telas;

import com.techcelladm.dal.ConexaoDAO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class TelaCadastroClientes extends JFrame {

    private JLabel lblNome;
    private JLabel lblTelefone;
    private JLabel lblEndereco;
    private JLabel lblEmail;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private JTextField txtEmail;
    private JButton btnCadastrar;

    public TelaCadastroClientes() {
        initComponents();
    }

    private void initComponents() {
        lblNome = new JLabel("Nome:");
        lblTelefone = new JLabel("Telefone:");
        lblEndereco = new JLabel("Endereço:");
        lblEmail = new JLabel("Email:");
        txtNome = new JTextField();
        txtTelefone = new JTextField();
        txtEndereco = new JTextField();
        txtEmail = new JTextField();
        btnCadastrar = new JButton("Cadastrar");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Clientes");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblNome)
                            .addComponent(lblTelefone)
                            .addComponent(lblEndereco)
                            .addComponent(lblEmail))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtTelefone)
                            .addComponent(txtEndereco)
                            .addComponent(txtEmail)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefone)
                    .addComponent(txtTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndereco)
                    .addComponent(txtEndereco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCadastrar)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
        setSize(400, 300);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
    }

    private void cadastrarCliente() {
    // Recupera os valores dos campos de texto
    String nome = txtNome.getText();
    String telefone = txtTelefone.getText();
    String endereco = txtEndereco.getText();
    String email = txtEmail.getText();

    // Estabelece a conexão com o banco de dados
    ConexaoDAO conexaoDAO = new ConexaoDAO();
    Connection conn = conexaoDAO.conectaBD();

    if (conn != null) {
        try {
            // Prepara a consulta SQL para inserir os dados do cliente
            String query = "INSERT INTO tabela_clientes (nome, telefone, endereco, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nome);
            statement.setString(2, telefone);
            statement.setString(3, endereco);
            statement.setString(4, email);

            // Executa a consulta para inserir os dados
            int linhasAfetadas = statement.executeUpdate();
            if (linhasAfetadas > 0) {
                JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
                // Limpa os campos após o cadastro
                limparCampos();
                this.dispose(); // Fecha a tela após o cadastro
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o cliente.");
            }

            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar o cliente.");
        }
    }
}

private void limparCampos() {
    txtNome.setText("");
    txtTelefone.setText("");
    txtEndereco.setText("");
    txtEmail.setText("");
}

    private void exibirClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
