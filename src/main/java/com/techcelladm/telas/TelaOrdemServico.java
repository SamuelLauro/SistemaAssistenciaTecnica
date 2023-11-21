package com.techcelladm.telas;

import com.techcelladm.dal.ConexaoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaOrdemServico extends JFrame {

    private JButton btnPesquisarCliente;
    private JButton btnAdicionarServico;
    private JButton btnFinalizarOrdem;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTableOrdemServico;
    private Connection conn;

    public TelaOrdemServico() {
        initComponents();
        conn = new ConexaoDAO().conectaBD(); // Conectar ao banco ao iniciar
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTableOrdemServico = new JTable();
        btnPesquisarCliente = new JButton();
        btnAdicionarServico = new JButton();
        btnFinalizarOrdem = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Serviço");

        jLabel1.setText("Lista de Ordens de Serviço");

        jTableOrdemServico.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Cliente", "Descrição", "Valor"}
        ));
        jScrollPane1.setViewportView(jTableOrdemServico);

        btnPesquisarCliente.setText("Pesquisar Cliente");
        btnPesquisarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPesquisarClienteActionPerformed(evt);
            }
        });

        btnAdicionarServico.setText("Adicionar Serviço");
        btnAdicionarServico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAdicionarServicoActionPerformed(evt);
            }
        });

        btnFinalizarOrdem.setText("Finalizar Ordem");
        btnFinalizarOrdem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnFinalizarOrdemActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnPesquisarCliente)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnAdicionarServico)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnFinalizarOrdem)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnPesquisarCliente)
                                        .addComponent(btnAdicionarServico)
                                        .addComponent(btnFinalizarOrdem))
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnPesquisarClienteActionPerformed(ActionEvent evt) {
        String nomeCliente = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");
        if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {
            pesquisarCliente(nomeCliente.toLowerCase());
        }
    }

    private void pesquisarCliente(String nomeCliente) {
        DefaultTableModel model = (DefaultTableModel) jTableOrdemServico.getModel();
        model.setRowCount(0); // Limpa a tabela antes de exibir os dados

        try {
            String query = "SELECT * FROM tabela_clientes WHERE nome LIKE ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, "%" + nomeCliente + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idCliente = resultSet.getInt("id_cliente");
                String nome = resultSet.getString("nome");

                model.addRow(new Object[]{idCliente, nome}); // Adiciona o cliente à tabela de ordens
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar clientes.");
        }
    }

    private void btnAdicionarServicoActionPerformed(ActionEvent evt) {
        String descricaoServico = JOptionPane.showInputDialog(this, "Digite a descrição do serviço:");
        double valorServico = Double.parseDouble(JOptionPane.showInputDialog(this, "Digite o valor do serviço:"));

        if (descricaoServico != null && !descricaoServico.trim().isEmpty()) {
            adicionarServico(descricaoServico, valorServico);
        }
    }

    private void adicionarServico(String descricao, double valor) {
        try {
            String query = "INSERT INTO servicos (descricao, valor) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, descricao);
            statement.setDouble(2, valor);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Serviço adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar o serviço.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar o serviço.");
        }
    }

    private void btnFinalizarOrdemActionPerformed(ActionEvent evt) {
        // Aqui você pode implementar a lógica para finalizar a ordem de serviço
        // Por exemplo, cálculos finais, atualizações no banco de dados, etc.
        // Neste exemplo, apenas exibe uma mensagem indicando a finalização da ordem.
        JOptionPane.showMessageDialog(this, "Ordem de serviço finalizada!");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaOrdemServico().setVisible(true);
            }
        });
    }
}
