package com.techcelladm.telas;

import com.techcelladm.dal.ConexaoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelaClientesCadastrados extends JFrame {

    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JButton btnCadastrar;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTableClientes;

    public TelaClientesCadastrados() {
        initComponents();
        exibirClientes(); // Exibe os clientes ao iniciar
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTableClientes = new JTable();
        btnPesquisar = new JButton();
        btnExcluir = new JButton();
        btnCadastrar = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clientes Cadastrados");

        jLabel1.setText("Lista de Clientes");

        jTableClientes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Telefone", "Endereço", "Email"}
        ));
        jScrollPane1.setViewportView(jTableClientes);

        btnPesquisar.setText("Pesquisar Cliente");
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir Cliente");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar Cliente");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
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
                                                .addComponent(btnPesquisar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnExcluir)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnCadastrar)))
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
                                        .addComponent(btnPesquisar)
                                        .addComponent(btnExcluir)
                                        .addComponent(btnCadastrar))
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnPesquisarActionPerformed(ActionEvent evt) {
        String nomeCliente = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");
        if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {
            pesquisarCliente(nomeCliente.toLowerCase());
        }
    }

    private void btnExcluirActionPerformed(ActionEvent evt) {
        int selectedRow = jTableClientes.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTableClientes.getModel();
            String nomeCliente = (String) model.getValueAt(selectedRow, 0); // Obtém o nome do cliente na linha selecionada

            // Lógica para excluir o cliente do banco de dados
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conn = conexaoDAO.conectaBD();

            if (conn != null) {
                try {
                    // Utilize aqui a lógica específica do seu banco para a exclusão do cliente
                    String query = "DELETE FROM tabela_clientes WHERE nome = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, nomeCliente);
                    int deletedRows = statement.executeUpdate();

                    if (deletedRows > 0) {
                        // Se a exclusão no banco for bem-sucedida, remova a linha da tabela na interface
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir o cliente.");
                    }

                    statement.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o cliente do banco de dados.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
    }

    private void btnCadastrarActionPerformed(ActionEvent evt) {
        // Abre a tela de cadastro de cliente
        TelaCadastroClientes telaCadastroClientes = new TelaCadastroClientes();
        telaCadastroClientes.setVisible(true);
        telaCadastroClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void exibirClientes() {
        DefaultTableModel model = (DefaultTableModel) jTableClientes.getModel();
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = conexaoDAO.conectaBD();

        if (conn != null) {
            try {
                String query = "SELECT nome, telefone, endereco, email FROM tabela_clientes";
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                model.setRowCount(0); // Limpa a tabela antes de exibir os dados

                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String telefone = resultSet.getString("telefone");
                    String endereco = resultSet.getString("endereco");
                    String email = resultSet.getString("email");

                    model.addRow(new Object[]{nome, telefone, endereco, email});
                }

                resultSet.close();
                statement.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao recuperar os clientes do banco de dados.");
            }
        }
    }

    private void pesquisarCliente(String nomeCliente) {
        DefaultTableModel model = (DefaultTableModel) jTableClientes.getModel();
        int rowCount = model.getRowCount();
        boolean clienteEncontrado = false;

        for (int i = 0; i < rowCount; i++) {
            String nome = ((String) model.getValueAt(i, 0)).toLowerCase();
            if (nome.contains(nomeCliente)) {
                jTableClientes.setRowSelectionInterval(i, i);
                jTableClientes.scrollRectToVisible(jTableClientes.getCellRect(i, 0, true));
                clienteEncontrado = true;
                break;
            }
        }

        if (!clienteEncontrado) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaClientesCadastrados().setVisible(true);
            }
        });
    }
}
