    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package bridging;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgRekeningTahun;

/**
 *
 * @author dosen
 */
public class AkunRekeningBankMandiri extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgRekeningTahun rekening=new DlgRekeningTahun(null,false);
    private int i=0;

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public AkunRekeningBankMandiri(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"Kode Akun","Akun Rekening","Kode Biaya","Akun Biaya Transaksi","Username","Password","Client ID","Client Secret","Kode Faskes","Kode MCM","No.Rekening"};
        tabMode=new DefaultTableModel(null,row){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

        tbSpesialis.setModel(tabMode);
        //tampil();
        //tbJabatan.setDefaultRenderer(Object.class, new WarnaTable(Scroll.getBackground(),Color.GREEN));
        tbSpesialis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSpesialis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbSpesialis.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(200);
            }else if(i==2){
                column.setPreferredWidth(65);
            }else if(i==3){
                column.setPreferredWidth(200);
            }else if(i==4){
                column.setPreferredWidth(200);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(200);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(130);
            }
        }

        tbSpesialis.setDefaultRenderer(Object.class, new WarnaTable());

        kdrek.setDocument(new batasInput((byte)15).getKata(kdrek));
        Username.setDocument(new batasInput((byte)32).getKata(Username));
        Password.setDocument(new batasInput((byte)32).getKata(Password));
        ClientID.setDocument(new batasInput((byte)32).getKata(ClientID));
        ClientSecret.setDocument(new batasInput((byte)32).getKata(ClientSecret));
        KodeMCM.setDocument(new batasInput((byte)8).getKata(KodeMCM));
        KodeFaskes.setDocument(new batasInput((byte)5).getKata(KodeFaskes));
        NoRekening.setDocument(new batasInput((byte)30).getOnlyAngka(NoRekening));
        
        rekening.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(rekening.getTabel().getSelectedRow()!= -1){      
                    if(i==1){
                        if(rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),3).toString().equals("N")&&
                                rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),4).toString().equals("D")){
                            kdrek.setText(rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),1).toString());
                            nmrek.setText(rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),2).toString()); 
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Rekening harus Tipe N dan Balance D..!!");
                        }

                        kdrek.requestFocus();
                    }else if(i==2){
                        if(rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),3).toString().equals("R")&&
                                rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),4).toString().equals("D")){
                            KdRekBiaya.setText(rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),1).toString());
                            NmRekBiaya.setText(rekening.getTabel().getValueAt(rekening.getTabel().getSelectedRow(),2).toString()); 
                        }else{
                            JOptionPane.showMessageDialog(rootPane,"Rekening harus Tipe R dan Balance D..!!");
                        }

                        KdRekBiaya.requestFocus();
                    }
                }    
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        rekening.getTabel().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    rekening.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });       
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSpesialis = new widget.Table();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        kdrek = new widget.TextBox();
        nmrek = new widget.TextBox();
        BtnPenjab = new widget.Button();
        jLabel4 = new widget.Label();
        Username = new widget.TextBox();
        Password = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel6 = new widget.Label();
        ClientID = new widget.TextBox();
        jLabel7 = new widget.Label();
        ClientSecret = new widget.TextBox();
        jLabel8 = new widget.Label();
        KodeFaskes = new widget.TextBox();
        jLabel9 = new widget.Label();
        KdRekBiaya = new widget.TextBox();
        NmRekBiaya = new widget.TextBox();
        BtnBiaya = new widget.Button();
        jLabel10 = new widget.Label();
        KodeMCM = new widget.TextBox();
        jLabel11 = new widget.Label();
        NoRekening = new widget.TextBox();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Setup Password & Akun Rekening Host to Host Bank Mandiri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSpesialis.setAutoCreateRowSorter(true);
        tbSpesialis.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSpesialis.setName("tbSpesialis"); // NOI18N
        tbSpesialis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSpesialisMouseClicked(evt);
            }
        });
        tbSpesialis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSpesialisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSpesialis);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(74, 196));
        panelGlass7.setLayout(null);

        jLabel3.setText("Akun Rekening :");
        jLabel3.setName("jLabel3"); // NOI18N
        panelGlass7.add(jLabel3);
        jLabel3.setBounds(1, 10, 90, 23);

        kdrek.setEditable(false);
        kdrek.setHighlighter(null);
        kdrek.setName("kdrek"); // NOI18N
        panelGlass7.add(kdrek);
        kdrek.setBounds(94, 10, 108, 23);

        nmrek.setEditable(false);
        nmrek.setHighlighter(null);
        nmrek.setName("nmrek"); // NOI18N
        panelGlass7.add(nmrek);
        nmrek.setBounds(205, 10, 282, 23);

        BtnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab.setMnemonic('1');
        BtnPenjab.setToolTipText("Alt+1");
        BtnPenjab.setName("BtnPenjab"); // NOI18N
        BtnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjabActionPerformed(evt);
            }
        });
        BtnPenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPenjabKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnPenjab);
        BtnPenjab.setBounds(490, 10, 28, 23);

        jLabel4.setText("Username :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass7.add(jLabel4);
        jLabel4.setBounds(1, 72, 90, 23);

        Username.setHighlighter(null);
        Username.setName("Username"); // NOI18N
        Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameKeyPressed(evt);
            }
        });
        panelGlass7.add(Username);
        Username.setBounds(94, 72, 150, 23);

        Password.setName("Password"); // NOI18N
        Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordKeyPressed(evt);
            }
        });
        panelGlass7.add(Password);
        Password.setBounds(368, 72, 150, 23);

        jLabel5.setText("Password :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(255, 72, 110, 23);

        jLabel6.setText("Client ID :");
        jLabel6.setName("jLabel6"); // NOI18N
        panelGlass7.add(jLabel6);
        jLabel6.setBounds(1, 102, 90, 23);

        ClientID.setHighlighter(null);
        ClientID.setName("ClientID"); // NOI18N
        ClientID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClientIDKeyPressed(evt);
            }
        });
        panelGlass7.add(ClientID);
        ClientID.setBounds(94, 102, 150, 23);

        jLabel7.setText("Client Secret :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass7.add(jLabel7);
        jLabel7.setBounds(255, 102, 110, 23);

        ClientSecret.setName("ClientSecret"); // NOI18N
        ClientSecret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ClientSecretKeyPressed(evt);
            }
        });
        panelGlass7.add(ClientSecret);
        ClientSecret.setBounds(368, 102, 150, 23);

        jLabel8.setText("Kode Faskes :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(1, 132, 90, 23);

        KodeFaskes.setHighlighter(null);
        KodeFaskes.setName("KodeFaskes"); // NOI18N
        KodeFaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeFaskesKeyPressed(evt);
            }
        });
        panelGlass7.add(KodeFaskes);
        KodeFaskes.setBounds(94, 132, 150, 23);

        jLabel9.setText("Akun Biaya Transaksi :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(1, 40, 120, 23);

        KdRekBiaya.setEditable(false);
        KdRekBiaya.setHighlighter(null);
        KdRekBiaya.setName("KdRekBiaya"); // NOI18N
        panelGlass7.add(KdRekBiaya);
        KdRekBiaya.setBounds(124, 40, 108, 23);

        NmRekBiaya.setEditable(false);
        NmRekBiaya.setHighlighter(null);
        NmRekBiaya.setName("NmRekBiaya"); // NOI18N
        panelGlass7.add(NmRekBiaya);
        NmRekBiaya.setBounds(235, 40, 252, 23);

        BtnBiaya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBiaya.setMnemonic('1');
        BtnBiaya.setToolTipText("Alt+1");
        BtnBiaya.setName("BtnBiaya"); // NOI18N
        BtnBiaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBiayaActionPerformed(evt);
            }
        });
        BtnBiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBiayaKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnBiaya);
        BtnBiaya.setBounds(490, 40, 28, 23);

        jLabel10.setText("Kode MCM :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(255, 132, 110, 23);

        KodeMCM.setName("KodeMCM"); // NOI18N
        KodeMCM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeMCMKeyPressed(evt);
            }
        });
        panelGlass7.add(KodeMCM);
        KodeMCM.setBounds(368, 132, 150, 23);

        jLabel11.setText("No.Rekening :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(1, 162, 90, 23);

        NoRekening.setHighlighter(null);
        NoRekening.setName("NoRekening"); // NOI18N
        NoRekening.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRekeningKeyPressed(evt);
            }
        });
        panelGlass7.add(NoRekening);
        NoRekening.setBounds(94, 162, 150, 23);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjabActionPerformed
        i=1;
        rekening.emptTeks();
        rekening.tampil();
        rekening.isCek();
        rekening.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        rekening.setLocationRelativeTo(internalFrame1);
        rekening.setVisible(true);
    }//GEN-LAST:event_BtnPenjabActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        rekening.dispose();
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(kdrek.getText().trim().equals("")||nmrek.getText().trim().equals("")){
            Valid.textKosong(BtnPenjab,"Akun Rekening");
        }else if(KdRekBiaya.getText().trim().equals("")||NmRekBiaya.getText().trim().equals("")){
            Valid.textKosong(BtnBiaya,"Akun Rekening Biaya Transaksi");
        }else if(Username.getText().trim().equals("")){
            Valid.textKosong(Username,"Username");
        }else if(Password.getText().trim().equals("")){
            Valid.textKosong(Password,"Password");
        }else if(ClientID.getText().trim().equals("")){
            Valid.textKosong(ClientID,"Client ID");
        }else if(ClientSecret.getText().trim().equals("")){
            Valid.textKosong(ClientSecret,"Client Password");
        }else if(KodeFaskes.getText().trim().equals("")){
            Valid.textKosong(KodeFaskes,"Kode Faskes");
        }else if(KodeMCM.getText().trim().equals("")){
            Valid.textKosong(KodeMCM,"Kode MCM");
        }else if(NoRekening.getText().trim().equals("")){
            Valid.textKosong(NoRekening,"No.Rekening");
        }else{
            if(tbSpesialis.getSelectedRow()>-1){
                Sequel.queryu("delete from set_akun_mandiri");
                if(Sequel.menyimpantf("set_akun_mandiri","?,?,aes_encrypt(?,'nur'),aes_encrypt(?,'windi'),aes_encrypt(?,'nur'),aes_encrypt(?,'windi'),?,?,?","Akun Rekening",9,new String[]{
                    kdrek.getText(),KdRekBiaya.getText(),Username.getText(),Password.getText(),ClientID.getText(),ClientSecret.getText(),KodeFaskes.getText(),KodeMCM.getText(),NoRekening.getText()
                })==true){
                    tampil();
                    emptTeks();
                }
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        Sequel.queryu("delete from set_akun_mandiri");
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,NoRekening,BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(kdrek.getText().trim().equals("")||nmrek.getText().trim().equals("")){
            Valid.textKosong(BtnPenjab,"Akun Rekening");
        }else if(KdRekBiaya.getText().trim().equals("")||NmRekBiaya.getText().trim().equals("")){
            Valid.textKosong(BtnBiaya,"Akun Rekening Biaya Transaksi");
        }else if(Username.getText().trim().equals("")){
            Valid.textKosong(Username,"Username");
        }else if(Password.getText().trim().equals("")){
            Valid.textKosong(Password,"Password");
        }else if(ClientID.getText().trim().equals("")){
            Valid.textKosong(ClientID,"Client ID");
        }else if(ClientSecret.getText().trim().equals("")){
            Valid.textKosong(ClientSecret,"Client Password");
        }else if(KodeFaskes.getText().trim().equals("")){
            Valid.textKosong(KodeFaskes,"Kode Faskes");
        }else if(KodeMCM.getText().trim().equals("")){
            Valid.textKosong(KodeMCM,"Kode MCM");
        }else if(NoRekening.getText().trim().equals("")){
            Valid.textKosong(NoRekening,"No.Rekening");
        }else if(tabMode.getRowCount()==0){
            if(Sequel.menyimpantf("set_akun_mandiri","?,?,aes_encrypt(?,'nur'),aes_encrypt(?,'windi'),aes_encrypt(?,'nur'),aes_encrypt(?,'windi'),?,?,?","Akun Rekening",9,new String[]{
                kdrek.getText(),KdRekBiaya.getText(),Username.getText(),Password.getText(),ClientID.getText(),ClientSecret.getText(),KodeFaskes.getText(),KodeMCM.getText(),NoRekening.getText()
            })==true){
                tampil();
                emptTeks();
            }
        }else if(tabMode.getRowCount()>0){
            JOptionPane.showMessageDialog(null,"Maaf, Hanya diijinkan satu akun pengaturan ...!!!!");
            Username.requestFocus();
        }
        
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void tbSpesialisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSpesialisKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSpesialisKeyPressed

    private void tbSpesialisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSpesialisMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSpesialisMouseClicked

    private void UsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameKeyPressed
        Valid.pindah(evt,BtnBiaya,Password);
    }//GEN-LAST:event_UsernameKeyPressed

    private void PasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordKeyPressed
        Valid.pindah(evt,Username,ClientID);
    }//GEN-LAST:event_PasswordKeyPressed

    private void BtnPenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPenjabKeyPressed
        Valid.pindah(evt,BtnKeluar,BtnBiaya);
    }//GEN-LAST:event_BtnPenjabKeyPressed

    private void ClientIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientIDKeyPressed
        Valid.pindah(evt,Password,ClientSecret);
    }//GEN-LAST:event_ClientIDKeyPressed

    private void ClientSecretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClientSecretKeyPressed
        Valid.pindah(evt,ClientID,KodeFaskes);
    }//GEN-LAST:event_ClientSecretKeyPressed

    private void KodeFaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeFaskesKeyPressed
        Valid.pindah(evt,ClientSecret,KodeMCM);
    }//GEN-LAST:event_KodeFaskesKeyPressed

    private void BtnBiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBiayaActionPerformed
        i=2;
        rekening.emptTeks();
        rekening.tampil();
        rekening.isCek();
        rekening.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        rekening.setLocationRelativeTo(internalFrame1);
        rekening.setVisible(true);
    }//GEN-LAST:event_BtnBiayaActionPerformed

    private void BtnBiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBiayaKeyPressed
        Valid.pindah(evt,BtnPenjab,Username);
    }//GEN-LAST:event_BtnBiayaKeyPressed

    private void KodeMCMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeMCMKeyPressed
        Valid.pindah(evt,KodeFaskes,NoRekening);
    }//GEN-LAST:event_KodeMCMKeyPressed

    private void NoRekeningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRekeningKeyPressed
        Valid.pindah(evt,KodeMCM,BtnSimpan);
    }//GEN-LAST:event_NoRekeningKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            AkunRekeningBankMandiri dialog = new AkunRekeningBankMandiri(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnBatal;
    private widget.Button BtnBiaya;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPenjab;
    private widget.Button BtnSimpan;
    private widget.TextBox ClientID;
    private widget.TextBox ClientSecret;
    private widget.TextBox KdRekBiaya;
    private widget.TextBox KodeFaskes;
    private widget.TextBox KodeMCM;
    private widget.TextBox NmRekBiaya;
    private widget.TextBox NoRekening;
    private widget.TextBox Password;
    private widget.ScrollPane Scroll;
    private widget.TextBox Username;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.TextBox kdrek;
    private widget.TextBox nmrek;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.Table tbSpesialis;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps=koneksi.prepareStatement(
                   "select set_akun_mandiri.kd_rek,akunmandiri.nm_rek,set_akun_mandiri.kd_rek_biaya,biaya.nm_rek,aes_decrypt(username,'nur'),aes_decrypt(set_akun_mandiri.password,'windi'),"+
                   "aes_decrypt(set_akun_mandiri.client_id,'nur'),aes_decrypt(set_akun_mandiri.client_secret,'windi'),set_akun_mandiri.kode_rs,set_akun_mandiri.kode_mcm,set_akun_mandiri.no_rekening "+
                   "from set_akun_mandiri inner join rekening as akunmandiri on set_akun_mandiri.kd_rek=akunmandiri.kd_rek "+
                   "inner join rekening as biaya on set_akun_mandiri.kd_rek_biaya=biaya.kd_rek "); 
            try{
                rs=ps.executeQuery();
                while(rs.next()){                
                    tabMode.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)
                    });
                }
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }            
    }

    public void emptTeks() {
        kdrek.setText("");
        nmrek.setText("");
        Username.setText("");
        Password.setText("");
        ClientID.setText("");
        ClientSecret.setText("");
        KodeFaskes.setText("");
        KdRekBiaya.setText("");
        NmRekBiaya.setText("");
        KodeMCM.setText("");
        NoRekening.setText("");
        BtnPenjab.requestFocus();
    }

    private void getData() {
        int row=tbSpesialis.getSelectedRow();
        if(row!= -1){
            kdrek.setText(tabMode.getValueAt(row,0).toString());
            nmrek.setText(tabMode.getValueAt(row,1).toString());
            KdRekBiaya.setText(tabMode.getValueAt(row,2).toString());
            NmRekBiaya.setText(tabMode.getValueAt(row,3).toString());
            Username.setText(tabMode.getValueAt(row,4).toString());
            Password.setText(tabMode.getValueAt(row,5).toString());
            ClientID.setText(tabMode.getValueAt(row,6).toString());
            ClientSecret.setText(tabMode.getValueAt(row,7).toString());
            KodeFaskes.setText(tabMode.getValueAt(row,8).toString());
            KodeMCM.setText(tabMode.getValueAt(row,9).toString());
            NoRekening.setText(tabMode.getValueAt(row,10).toString());
        }
    }

}
