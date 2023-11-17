package com.techcelladm.telas;

import javax.swing.*;

public class TelaOrdemServico extends javax.swing.JFrame {

    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabelCliente;
    private javax.swing.JLabel jLabelProduto;
    private javax.swing.JLabel jLabelDefeito;
    private javax.swing.JLabel jLabelServico;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtServico;

    public TelaOrdemServico() {
        initComponents();
    }

    private void initComponents() {
        jLabelCliente = new javax.swing.JLabel();
        jLabelProduto = new javax.swing.JLabel();
        jLabelDefeito = new javax.swing.JLabel();
        jLabelServico = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        txtProduto = new javax.swing.JTextField();
        txtDefeito = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Serviço");

        jLabelCliente.setText("Cliente:");

        jLabelProduto.setText("Produto:");

        jLabelDefeito.setText("Defeito:");

        jLabelServico.setText("Serviço:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        // ... (layout dos componentes omitido para manter o tamanho da resposta)

        // Layout dos componentes
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    // Adicionando os campos à tela
                    .addComponent(jLabelCliente)
                    .addComponent(jLabelProduto)
                    .addComponent(jLabelDefeito)
                    .addComponent(jLabelServico))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtProduto)
                    .addComponent(txtDefeito)
                    .addComponent(txtServico))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCliente)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProduto)
                    .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDefeito)
                    .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelServico)
                    .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        // Lógica para salvar os dados da ordem de serviço no banco de dados
        // Aqui você pode pegar os valores dos campos (txtCliente.getText(), etc.)
        // e salvar no banco de dados.
        // Se precisar de ajuda com a conexão com o banco ou lógica de salvamento, me avise.
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaOrdemServico().setVisible(true);
            }
        });
    }
}
