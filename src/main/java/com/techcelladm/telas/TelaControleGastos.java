package com.techcelladm.telas;

import com.techcelladm.dal.ConexaoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaControleGastos extends javax.swing.JFrame {

    public TelaControleGastos() {
        initComponents();
        exibirRelatorioGastos(); // Exibe o relatório de gastos ao iniciar
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdicionarGasto = new javax.swing.JButton();
        btnExcluirGasto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Gastos");

        jLabel1.setText("Lista de Gastos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Descrição", "Valor"}
        ));
        jScrollPane1.setViewportView(jTable1);

        btnAdicionarGasto.setText("Adicionar Gasto");
        btnAdicionarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarGastoActionPerformed(evt);
            }
        });

        btnExcluirGasto.setText("Excluir Gasto");
        btnExcluirGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirGastoActionPerformed(evt);
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
                        .addComponent(btnAdicionarGasto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluirGasto)))
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
                    .addComponent(btnAdicionarGasto)
                    .addComponent(btnExcluirGasto))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnAdicionarGastoActionPerformed(java.awt.event.ActionEvent evt) {
        JTextField descricaoField = new JTextField();
        JTextField valorField = new JTextField();
        Object[] message = {
            "Descrição:", descricaoField,
            "Valor:", valorField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Adicionar Novo Gasto", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String descricao = descricaoField.getText();
            double valor = Double.parseDouble(valorField.getText());

            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conn = conexaoDAO.conectaBD();

            if (conn != null) {
                try {
                    String query = "INSERT INTO tabela_gastos (descricao, valor) VALUES (?, ?)";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, descricao);
                    statement.setDouble(2, valor);
                    statement.executeUpdate();

                    exibirRelatorioGastos();

                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Erro na conexão com o banco de dados");
            }
        }
    }

    private void btnExcluirGastoActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String descricao = (String) model.getValueAt(selectedRow, 0);
            double valor = (double) model.getValueAt(selectedRow, 1);

            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conn = conexaoDAO.conectaBD();

            if (conn != null) {
                try {
                    String deleteQuery = "DELETE FROM tabela_gastos WHERE descricao = ? AND valor = ?";
                    PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                    deleteStatement.setString(1, descricao);
                    deleteStatement.setDouble(2, valor);
                    int deletedRows = deleteStatement.executeUpdate();

                    if (deletedRows > 0) {
                        exibirRelatorioGastos();
                    }

                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Erro na conexão com o banco de dados");
            }
        } else {
            System.out.println("Nenhum gasto selecionado para exclusão");
        }
    }

    private void exibirRelatorioGastos() {
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = conexaoDAO.conectaBD();

        if (conn != null) {
            try {
                String query = "SELECT descricao, valor FROM tabela_gastos";
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet result = statement.executeQuery();

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Descrição");
                model.addColumn("Valor");

                while (result.next()) {
                    String descricao = result.getString("descricao");
                    double valor = result.getDouble("valor");

                    model.addRow(new Object[]{descricao, valor});
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
                new TelaControleGastos().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnAdicionarGasto;
    private javax.swing.JButton btnExcluirGasto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
