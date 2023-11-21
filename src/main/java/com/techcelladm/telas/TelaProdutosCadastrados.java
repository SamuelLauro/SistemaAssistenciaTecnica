package com.techcelladm.telas;

import com.techcelladm.dal.ConexaoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TelaProdutosCadastrados extends JFrame {

    private JButton btnExcluir;
    private JButton btnPesquisar;
    private JButton btnCadastrar;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JTable jTableProdutos;

    public TelaProdutosCadastrados() {
        initComponents();
        exibirProdutos(); // Exibe os produtos ao iniciar
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTableProdutos = new JTable();
        btnPesquisar = new JButton();
        btnExcluir = new JButton();
        btnCadastrar = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos Cadastrados");

        jLabel1.setText("Lista de Produtos");

        jTableProdutos.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Preço", "Descrição"}
        ));
        jScrollPane1.setViewportView(jTableProdutos);

        btnPesquisar.setText("Pesquisar Produto");
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir Produto");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Cadastrar Produto");
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
        String nomeProduto = JOptionPane.showInputDialog(this, "Digite o nome do produto:");
        if (nomeProduto != null && !nomeProduto.trim().isEmpty()) {
            pesquisarProduto(nomeProduto.toLowerCase());
        }
    }

    private void btnExcluirActionPerformed(ActionEvent evt) {
        int selectedRow = jTableProdutos.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) jTableProdutos.getModel();
            String nomeProduto = (String) model.getValueAt(selectedRow, 0); // Obtém o nome do produto na linha selecionada

            // Lógica para excluir o produto do banco de dados
            ConexaoDAO conexaoDAO = new ConexaoDAO();
            Connection conn = conexaoDAO.conectaBD();

            if (conn != null) {
                try {
                    String query = "DELETE FROM tabela_produtos WHERE nome = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, nomeProduto);
                    int deletedRows = statement.executeUpdate();

                    if (deletedRows > 0) {
                        // Se a exclusão no banco for bem-sucedida, remova a linha da tabela na interface
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao excluir o produto.");
                    }

                    statement.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erro ao excluir o produto do banco de dados.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
        }
    }

    private void btnCadastrarActionPerformed(ActionEvent evt) {
        // Lógica para abrir a tela de cadastro de produto
        TelaCadastroProdutos telaCadastroProdutos = new TelaCadastroProdutos();
        telaCadastroProdutos.setVisible(true);
        telaCadastroProdutos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCadastroProdutos.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                exibirProdutos();
            }
        });
    }

    private void exibirProdutos() {
        DefaultTableModel model = (DefaultTableModel) jTableProdutos.getModel();
        ConexaoDAO conexaoDAO = new ConexaoDAO();
        Connection conn = conexaoDAO.conectaBD();

        if (conn != null) {
            try {
                String query = "SELECT nome, preco, descricao FROM tabela_produtos";
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                model.setRowCount(0); // Limpa a tabela antes de exibir os dados

                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");
                    String descricao = resultSet.getString("descricao");

                    model.addRow(new Object[]{nome, preco, descricao});
                }

                resultSet.close();
                statement.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao recuperar os produtos do banco de dados.");
            }
        }
    }

    private void pesquisarProduto(String nomeProduto) {
        DefaultTableModel model = (DefaultTableModel) jTableProdutos.getModel();
        int rowCount = model.getRowCount();
        boolean produtoEncontrado = false;

        for (int i = 0; i < rowCount; i++) {
            String nome = ((String) model.getValueAt(i, 0)).toLowerCase();
            if (nome.contains(nomeProduto)) {
                jTableProdutos.setRowSelectionInterval(i, i);
                jTableProdutos.scrollRectToVisible(jTableProdutos.getCellRect(i, 0, true));
                produtoEncontrado = true;
                break;
            }
        }

        if (!produtoEncontrado) {
            JOptionPane.showMessageDialog(this, "Produto não encontrado.");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProdutosCadastrados().setVisible(true);
            }
        });
    }
}
