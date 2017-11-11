/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archive;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author rafae
 */
public class TelaPrincipal extends javax.swing.JFrame {
    File arq;
    Arquivador archive = new Arquivador(null);
         
    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        TelaPrincipal.super.setSize(861, 501);
        this.setLocationRelativeTo(null);
        this.setTitle("Archive");
        Container c = this.getContentPane();
        c.setBackground(new Color(182, 230, 245));
        txtArquivo.setDisabledTextColor(Color.black);
        txtArquivadorDiretorio.setDisabledTextColor(Color.black);
        txtArquivadorExtensao.setDisabledTextColor(Color.black);
        tblArquivos.setSelectionModel(new ForcedListSelectionModel());
        tblArquivos.setModel(new ArquivosTableModel(null));
        gerarTabelaArquivos();
    }

    private void gerarTabelaArquivos(){
        tblArquivos.getTableHeader().setFont(new Font("Courier New", Font.BOLD, 15));
        tblArquivos.getColumnModel().getColumn(1).setMaxWidth(200);
        tblArquivos.getColumnModel().getColumn(1).setPreferredWidth(200);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblBackground = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtArquivo = new javax.swing.JTextField();
        btnEscolherArquivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArquivos = new javax.swing.JTable();
        btnAdicionarArquivo = new javax.swing.JButton();
        txtArquivadorDiretorio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnRemoverArquivo = new javax.swing.JButton();
        btnExtrairArquivo = new javax.swing.JButton();
        btnExtrairTudo = new javax.swing.JButton();
        btnEscolherDiretorio = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtArquivadorNome = new javax.swing.JTextField();
        txtArquivadorExtensao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);
        jPanel1.add(lblBackground);
        lblBackground.setBounds(0, 0, 850, 500);

        jPanel2.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel1.setText("Arquivo");

        txtArquivo.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtArquivo.setEnabled(false);

        btnEscolherArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscolherArquivoActionPerformed(evt);
            }
        });

        tblArquivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblArquivos);

        btnAdicionarArquivo.setText("Add");
        btnAdicionarArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarArquivoActionPerformed(evt);
            }
        });

        txtArquivadorDiretorio.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtArquivadorDiretorio.setEnabled(false);
        txtArquivadorDiretorio.setPreferredSize(new java.awt.Dimension(700, 24));

        jLabel2.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel2.setText("Diretório do Archive");

        btnRemoverArquivo.setText("Remover");
        btnRemoverArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverArquivoActionPerformed(evt);
            }
        });

        btnExtrairArquivo.setText("Extrair");
        btnExtrairArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrairArquivoActionPerformed(evt);
            }
        });

        btnExtrairTudo.setText("Extrair Tudo");
        btnExtrairTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrairTudoActionPerformed(evt);
            }
        });

        btnEscolherDiretorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscolherDiretorioActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        jLabel3.setText("Nome do Archive");

        txtArquivadorNome.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtArquivadorNome.setText("archive");
        txtArquivadorNome.setPreferredSize(new java.awt.Dimension(700, 24));
        txtArquivadorNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtArquivadorNomeFocusLost(evt);
            }
        });

        txtArquivadorExtensao.setFont(new java.awt.Font("Courier New", 0, 15)); // NOI18N
        txtArquivadorExtensao.setText(".got");
        txtArquivadorExtensao.setEnabled(false);
        txtArquivadorExtensao.setPreferredSize(new java.awt.Dimension(700, 24));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(323, 323, 323)
                        .addComponent(jLabel3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtArquivadorDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEscolherDiretorio)
                                .addGap(12, 12, 12)
                                .addComponent(txtArquivadorNome, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEscolherArquivo)
                            .addComponent(txtArquivadorExtensao, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(btnAdicionarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnExtrairArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btnExtrairTudo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEscolherArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArquivadorDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtArquivadorNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtArquivadorExtensao, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnEscolherDiretorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExtrairArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExtrairTudo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 850, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEscolherArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscolherArquivoActionPerformed
        String cwd = System.getProperty("user.dir");
        JFileChooser jfc = new JFileChooser(cwd);
        jfc.setDialogTitle("Escolher arquivo");
        if (jfc.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        arq = jfc.getSelectedFile();
        txtArquivo.setText(arq.toString());
    }//GEN-LAST:event_btnEscolherArquivoActionPerformed

    private void btnAdicionarArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarArquivoActionPerformed
        try {
            if (arq.getName().length() > 100) {
                // Exibe mensagem de "o arquivo deve possuir um nome com até 100 caracteres"
                return;
            }
            if (arq.length() >= 100000000) {
                // Exibe mensagem de "o arquivo deve possuir até 99 MB"
                return;
            }
            if (archive.getQuantidadeArquivos() == 99) {
                // Exibe mensagem de "o limite de arquivos foi alcançado (99)"
                return;
            }
            if (txtArquivadorDiretorio.getText().length() == 0) {
                // Exibe mensagem de "selecione um diretório para o archive"
                return;
            }
            if (txtArquivadorNome.getText().length() == 0) {
                // Exibe mensagem de "digite o nome do archive"
                return;
            }
            for (Arquivo arquivo : archive.getArquivos()) {
                if (arq.getName().equals(arquivo.getNome())) {
                    // Exibe mensagem de "já existe um arquivo com este nome"
                    return;
                }
            }
            try {
                FileInputStream fileInput = new FileInputStream(arq);
                ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                try {
                    for (int readNum; (readNum = fileInput.read(buf)) != -1;) {
                        byteOutput.write(buf, 0, readNum);
                    }
                } catch (IOException ex) {
                }
                byte[] bytes = byteOutput.toByteArray();

                Arquivo a = new Arquivo();
                a.setNome(arq.getName());
                a.setTamanho(byteOutput.size());
                archive.addArquivos(a);
                tblArquivos.setModel(new ArquivosTableModel(archive.getArquivos()));
                gerarTabelaArquivos();
                File fileArchive = new File(txtArquivadorDiretorio.getText() + "\\" + txtArquivadorNome.getText() + txtArquivadorExtensao.getText());
                archive.salvar(fileArchive);
                
                byteOutput.close();
                fileInput.close();
                // Exibir mensagem de "O arquivo foi adicionado com sucesso ao arquivador "txtArquivador.getText()   .got."

                /*File someFile = new File(arq.getName());
                FileOutputStream fos = new FileOutputStream(someFile);
                fos.write(bytes);
                fos.flush();
                fos.close();*/
                txtArquivo.setText("");
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            } 
        } catch (NullPointerException ex) { // Mensagem selecione um arquivo
        }
    }//GEN-LAST:event_btnAdicionarArquivoActionPerformed

    private void btnRemoverArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverArquivoActionPerformed
        if (tblArquivos.getSelectedRow() == -1) {
            // Exibir mensagem "selecione um arquivo"
        }
        else {
            
        }
    }//GEN-LAST:event_btnRemoverArquivoActionPerformed

    private void btnExtrairArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrairArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExtrairArquivoActionPerformed

    private void btnExtrairTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrairTudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExtrairTudoActionPerformed

    private void btnEscolherDiretorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscolherDiretorioActionPerformed
        JFileChooser jfc = new JFileChooser(); 
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("Escolher diretório");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            txtArquivadorDiretorio.setText(jfc.getSelectedFile().toString());
        }
    }//GEN-LAST:event_btnEscolherDiretorioActionPerformed

    private void txtArquivadorNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtArquivadorNomeFocusLost
        if (txtArquivadorDiretorio.getText().length() > 0) {
            File fileArchive = new File(txtArquivadorDiretorio.getText() + "\\" + txtArquivadorNome.getText() + txtArquivadorExtensao.getText());
            Arquivador archive = new Arquivador(fileArchive);
        }
    }//GEN-LAST:event_txtArquivadorNomeFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarArquivo;
    private javax.swing.JButton btnEscolherArquivo;
    private javax.swing.JButton btnEscolherDiretorio;
    private javax.swing.JButton btnExtrairArquivo;
    private javax.swing.JButton btnExtrairTudo;
    private javax.swing.JButton btnRemoverArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JTable tblArquivos;
    private javax.swing.JTextField txtArquivadorDiretorio;
    private javax.swing.JTextField txtArquivadorExtensao;
    private javax.swing.JTextField txtArquivadorNome;
    private javax.swing.JTextField txtArquivo;
    // End of variables declaration//GEN-END:variables
}
