package com.techcelladm.telas;

import com.techcelladm.dal.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaControleVendas extends javax.swing.JFrame {

    public TelaControleVendas() {
        initComponents();
        exibirRelatorioVendas(); // Exibe o relatório de vendas ao iniciar
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdicionarVenda = new javax.swing.JButton();
        btnExcluirVenda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Vendas");

        jLabel1.setText("Lista de Vendas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Produto", "Preço"}
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAdicionarVenda.setText("Adicionar Venda");
        btnAdicionarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarVendaActionPerformed(evt);
            }
        });

        btnExcluirVenda.setText("Excluir Venda");
        btnExcluirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionarVenda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluirVenda)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdicionarVenda)
                    .addComponent(btnExcluirVenda))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnAdicionarVendaActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField produtoField = new JTextField();
        JTextField precoField = new JTextField();
        Object[] message = {
        "Produto:", produtoField,
        "Preço:", precoField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Adicionar Nova Venda", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
        String produto = produtoField.getText();
        double preco = Double.parseDouble(precoField.getText());

        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = conexaoDAO.conectaBD();

        if (conn != null) {
           try {
               String query = "INSERT INTO tabela_vendas (produto, preco) VALUES (?, ?)";
               PreparedStatement statement = conn.prepareStatement(query);
               statement.setString(1, produto);
               statement.setDouble(2, preco);
               statement.executeUpdate();

               exibirRelatorioVendas(); // Atualiza a tabela após a inserção

               conn.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
        } else {
           System.out.println("Erro na conexão com o banco de dados");
        }
    }
    }

    private void btnExcluirVendaActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String produto = (String) model.getValueAt(selectedRow, 0);
        double preco = (double) model.getValueAt(selectedRow, 1);

        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = conexaoDAO.conectaBD();

        if (conn != null) {
            try {
                String deleteQuery = "DELETE FROM tabela_vendas WHERE produto = ? AND preco = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                deleteStatement.setString(1, produto);
                deleteStatement.setDouble(2, preco);
                int deletedRows = deleteStatement.executeUpdate();

                if (deletedRows > 0) {
                    exibirRelatorioVendas(); // Atualiza a tabela após a exclusão
                }

                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Erro na conexão com o banco de dados");
        }
        } else {
        System.out.println("Nenhuma venda selecionada para exclusão");
        }
    }

    private void exibirRelatorioVendas() {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = conexaoDAO.conectaBD();

        if (conn != null) {
        try {
            String query = "SELECT produto, preco FROM tabela_vendas";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Produto");
            model.addColumn("Preço");

            while (result.next()) {
                String produto = result.getString("produto");
                double preco = result.getDouble("preco");

                model.addRow(new Object[]{produto, preco});
            }

            jTable1.setModel(model);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        } else {
        System.out.println("Erro na conexão com o banco de dados");
        }
        }

        public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaControleVendas().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnAdicionarVenda;
    private javax.swing.JButton btnExcluirVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
