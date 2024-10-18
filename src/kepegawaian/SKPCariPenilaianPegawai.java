/*
  Dilarang keras memperjualbelikan/mengambil keuntungan dari Software 
  ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package kepegawaian;
import fungsi.akses;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class SKPCariPenilaianPegawai extends javax.swing.JDialog {
    private final validasi Valid=new validasi();
    private final Connection koneksi=koneksiDB.condb();
    private DlgCariPegawai pegawai=new DlgCariPegawai(null,false);
    private DlgCariSKPKategoriPenilaian kategori=new DlgCariSKPKategoriPenilaian(null,false);
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private final sekuel Sequel=new sekuel();
    private int i;
    private StringBuilder htmlContent;

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public SKPCariPenilaianPegawai(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        NoPenilaian.setDocument(new batasInput((int)20).getKata(NoPenilaian));  
        TCari.setDocument(new batasInput((int)100).getKata(TCari));          
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }  
        
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pegawai.getTable().getSelectedRow()!= -1){    
                    if(i==1){
                        KdPenilai.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),0).toString());
                        NmPenilai.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),1).toString());
                    }else if(i==2){
                        KdDInilai.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),0).toString());
                        NmDinilai.setText(pegawai.tbKamar.getValueAt(pegawai.tbKamar.getSelectedRow(),1).toString());
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
        
        pegawai.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pegawai.dispose();
                }                
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kategori.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kategori.getTable().getSelectedRow()!= -1){
                    KdKategori.setText(kategori.getTable().getValueAt(kategori.getTable().getSelectedRow(),0).toString());
                    NmKategori.setText(kategori.getTable().getValueAt(kategori.getTable().getSelectedRow(),1).toString());
                    btnKategori.requestFocus();
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
        
        kategori.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kategori.dispose();
                }                
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        ChkInput.setSelected(false);
        isForm();
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML2.setEditable(true);
        LoadHTML2.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"+
                ".head td{border-right: 1px solid #777777;font: 8.5px tahoma;height:10px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
        );
        
        Document doc = kit.createDefaultDocument();
        LoadHTML2.setDocument(doc);
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
        scrollPane1 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        jPanel1 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        jLabel11 = new widget.Label();
        Status = new widget.ComboBox();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnPrint = new widget.Button();
        label9 = new widget.Label();
        LTotal = new widget.Label();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        jLabel9 = new widget.Label();
        KdKategori = new widget.TextBox();
        btnKategori = new widget.Button();
        NmKategori = new widget.TextBox();
        jLabel10 = new widget.Label();
        Sasaran = new widget.ComboBox();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label11 = new widget.Label();
        Tanggal1 = new widget.Tanggal();
        label12 = new widget.Label();
        Tanggal2 = new widget.Tanggal();
        label13 = new widget.Label();
        KdPenilai = new widget.TextBox();
        NmPenilai = new widget.TextBox();
        btnPenilai = new widget.Button();
        label17 = new widget.Label();
        KdDInilai = new widget.TextBox();
        NmDinilai = new widget.TextBox();
        btnDinilai = new widget.Button();
        NoPenilaian = new widget.TextBox();
        label15 = new widget.Label();
        BtnHapus = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Cari Data Penilaian Petugas/Dokter Dalam Implementasi Sasaran Keselamatan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        scrollPane1.setViewportView(LoadHTML2);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel11.setText("Status :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(47, 23));
        panelisi1.add(jLabel11);

        Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Proses Penilaian", "Keluar Hasil" }));
        Status.setName("Status"); // NOI18N
        Status.setPreferredSize(new java.awt.Dimension(130, 23));
        Status.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                StatusKeyPressed(evt);
            }
        });
        panelisi1.add(Status);

        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(62, 23));
        panelisi1.add(label10);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(185, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('5');
        BtnCari.setToolTipText("Alt+5");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelisi1.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelisi1.add(BtnAll);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelisi1.add(BtnPrint);

        label9.setText("Record :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi1.add(label9);

        LTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LTotal.setText("0");
        LTotal.setName("LTotal"); // NOI18N
        LTotal.setPreferredSize(new java.awt.Dimension(57, 23));
        panelisi1.add(LTotal);

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
        panelisi1.add(BtnKeluar);

        jPanel1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(null);

        jLabel9.setText("Kategori :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi4.add(jLabel9);
        jLabel9.setBounds(0, 10, 62, 23);

        KdKategori.setEditable(false);
        KdKategori.setHighlighter(null);
        KdKategori.setName("KdKategori"); // NOI18N
        panelisi4.add(KdKategori);
        KdKategori.setBounds(66, 10, 70, 23);

        btnKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKategori.setMnemonic('1');
        btnKategori.setToolTipText("Alt+1");
        btnKategori.setName("btnKategori"); // NOI18N
        btnKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKategoriActionPerformed(evt);
            }
        });
        btnKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKategoriKeyPressed(evt);
            }
        });
        panelisi4.add(btnKategori);
        btnKategori.setBounds(360, 10, 28, 23);

        NmKategori.setEditable(false);
        NmKategori.setName("NmKategori"); // NOI18N
        panelisi4.add(NmKategori);
        NmKategori.setBounds(138, 10, 220, 23);

        jLabel10.setText("Sasaran :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi4.add(jLabel10);
        jLabel10.setBounds(390, 10, 60, 23);

        Sasaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "1. Mengidentifikasi Pasien Dengan Benar", "2. Meningkatkan Komunikasi Yang Efektif", "3. Meningkatkan Keamanan Obat-obatan Yang Harus Diwaspadai", "4. Memastikan Lokasi Pembedahan Yang Benar, Prosedur Yang Benar, Pembedahan Pada Pasien Yang Benar", "5. Mengurangi Risiko Infeksi Akibat Perawatan Kesehatan", "6. Mengurangi Risiko Cidera Pasien Akibat Terjatuh" }));
        Sasaran.setName("Sasaran"); // NOI18N
        Sasaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SasaranKeyPressed(evt);
            }
        });
        panelisi4.add(Sasaran);
        Sasaran.setBounds(454, 10, 302, 23);

        jPanel1.add(panelisi4, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 95));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Filter Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 74));
        FormInput.setLayout(null);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(0, 40, 60, 23);

        Tanggal1.setDisplayFormat("dd-MM-yyyy");
        Tanggal1.setName("Tanggal1"); // NOI18N
        Tanggal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal1KeyPressed(evt);
            }
        });
        FormInput.add(Tanggal1);
        Tanggal1.setBounds(64, 40, 90, 23);

        label12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label12.setText("s.d.");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label12);
        label12.setBounds(157, 40, 27, 23);

        Tanggal2.setDisplayFormat("dd-MM-yyyy");
        Tanggal2.setName("Tanggal2"); // NOI18N
        Tanggal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tanggal2KeyPressed(evt);
            }
        });
        FormInput.add(Tanggal2);
        Tanggal2.setBounds(187, 40, 90, 23);

        label13.setText("Yang Menilai :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label13);
        label13.setBounds(290, 10, 90, 23);

        KdPenilai.setEditable(false);
        KdPenilai.setName("KdPenilai"); // NOI18N
        KdPenilai.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdPenilai);
        KdPenilai.setBounds(384, 10, 120, 23);

        NmPenilai.setEditable(false);
        NmPenilai.setName("NmPenilai"); // NOI18N
        NmPenilai.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPenilai);
        NmPenilai.setBounds(506, 10, 220, 23);

        btnPenilai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenilai.setMnemonic('2');
        btnPenilai.setToolTipText("Alt+2");
        btnPenilai.setName("btnPenilai"); // NOI18N
        btnPenilai.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPenilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenilaiActionPerformed(evt);
            }
        });
        btnPenilai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPenilaiKeyPressed(evt);
            }
        });
        FormInput.add(btnPenilai);
        btnPenilai.setBounds(728, 10, 28, 23);

        label17.setText("Yang Dinilai :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(290, 40, 90, 23);

        KdDInilai.setEditable(false);
        KdDInilai.setName("KdDInilai"); // NOI18N
        KdDInilai.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDInilai);
        KdDInilai.setBounds(384, 40, 120, 23);

        NmDinilai.setEditable(false);
        NmDinilai.setName("NmDinilai"); // NOI18N
        NmDinilai.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDinilai);
        NmDinilai.setBounds(506, 40, 220, 23);

        btnDinilai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDinilai.setMnemonic('2');
        btnDinilai.setToolTipText("Alt+2");
        btnDinilai.setName("btnDinilai"); // NOI18N
        btnDinilai.setPreferredSize(new java.awt.Dimension(28, 23));
        btnDinilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDinilaiActionPerformed(evt);
            }
        });
        btnDinilai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDinilaiKeyPressed(evt);
            }
        });
        FormInput.add(btnDinilai);
        btnDinilai.setBounds(728, 40, 28, 23);

        NoPenilaian.setName("NoPenilaian"); // NOI18N
        NoPenilaian.setPreferredSize(new java.awt.Dimension(207, 23));
        NoPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoPenilaianKeyPressed(evt);
            }
        });
        FormInput.add(NoPenilaian);
        NoPenilaian.setBounds(64, 10, 183, 23);

        label15.setText("Nomor :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(label15);
        label15.setBounds(0, 10, 60, 23);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapus);
        BtnHapus.setBounds(249, 10, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        pegawai.dispose();
        kategori.dispose();
        dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){   
            pegawai.dispose();
            kategori.dispose();
            dispose();              
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            Status.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        NoPenilaian.setText("");
        KdPenilai.setText("");
        NmPenilai.setText("");
        KdDInilai.setText("");
        NmDinilai.setText("");
        KdKategori.setText("");
        NmKategori.setText("");
        Sasaran.setSelectedIndex(0);
        Status.setSelectedIndex(0);
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            File g = new File("filepenilaian.css");            
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".head td{border-right: 1px solid #777777;font: 8.5px tahoma;height:10px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi a{text-decoration:none;color:#8b9b95;padding:0 0 0 0px;font-family: Tahoma;font-size: 8.5px;}"+
                ".isi2 td{font: 8.5px tahoma;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
            );
            bg.close();

            File f = new File("LaporanPenilaianSKP.html");            
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
            bw.write(LoadHTML2.getText().replaceAll("<head>","<head>"+
                    "<link href=\"filepenilaian.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                    "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                        "<tr class='isi2'>"+
                            "<td valign='top' align='center'>"+
                                "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                "<font size='2' face='Tahoma'>Laporan Penilaian Petugas/Dokter Dalam Implementasi Sasaran Keselamatan Pasien<br><br></font>"+        
                            "</td>"+
                       "</tr>"+
                    "</table>")
            );
            bw.close();                         
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnAll,BtnAll);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void Tanggal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal2KeyPressed
        Valid.pindah2(evt,Tanggal1,btnPenilai);
    }//GEN-LAST:event_Tanggal2KeyPressed

    private void Tanggal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tanggal1KeyPressed
        Valid.pindah2(evt,NoPenilaian,Tanggal2);
    }//GEN-LAST:event_Tanggal1KeyPressed

    private void btnPenilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenilaiActionPerformed
        i=1;
        pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
    }//GEN-LAST:event_btnPenilaiActionPerformed

    private void btnDinilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinilaiActionPerformed
        i=2;
        pegawai.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
    }//GEN-LAST:event_btnDinilaiActionPerformed

    private void NoPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoPenilaianKeyPressed
        Valid.pindah(evt, TCari, btnPenilai);
    }//GEN-LAST:event_NoPenilaianKeyPressed

    private void btnKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKategoriActionPerformed
        kategori.isCek();
        kategori.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        kategori.setLocationRelativeTo(internalFrame1);
        kategori.setVisible(true);
    }//GEN-LAST:event_btnKategoriActionPerformed

    private void btnKategoriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKategoriKeyPressed
        Valid.pindah(evt,btnDinilai,Sasaran);
    }//GEN-LAST:event_btnKategoriKeyPressed

    private void SasaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SasaranKeyPressed
        Valid.pindah(evt,btnKategori,Status);
    }//GEN-LAST:event_SasaranKeyPressed

    private void StatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StatusKeyPressed
        Valid.pindah(evt,Sasaran,TCari);
    }//GEN-LAST:event_StatusKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        htmlContent = new StringBuilder();
        htmlContent.append(
            "<tr class='head'>"+
                "<td valign='top' bgcolor='#FFFAFA' align='center' width='95px'>No.Penilaian</td>"+
                "<td valign='top' bgcolor='#FFFAFA' align='center' width='200px'>Yang Dinilai</td>"+
                "<td valign='top' bgcolor='#FFFAFA' align='center' width='200px'>Yang Menilai</td>"+
                "<td valign='top' bgcolor='#FFFAFA' align='center' width='105px'>Tanggal</td>"+
                "<td valign='top' bgcolor='#FFFAFA' align='center' width='300px'>Keterangan</td>"+
                "<td valign='top' bgcolor='#FFFAFA' align='center' width='100px'>Status</td>"+
            "</tr>");
        LoadHTML2.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='left' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
    }//GEN-LAST:event_formWindowOpened

    private void btnPenilaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPenilaiKeyPressed
        Valid.pindah(evt,NoPenilaian,btnDinilai);
    }//GEN-LAST:event_btnPenilaiKeyPressed

    private void btnDinilaiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDinilaiKeyPressed
        Valid.pindah(evt,btnPenilai,btnKategori);
    }//GEN-LAST:event_btnDinilaiKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(NoPenilaian.getText().trim().equals("")){
            Valid.textKosong(NoPenilaian,"No.Penilaian");
        }else {
            if(Valid.SetInteger(LTotal.getText())>0){
                if(Sequel.meghapustf("skp_penilaian","nomor_penilaian",NoPenilaian.getText())==true){
                    tampil();
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Pastikan nomor penilaian yang mau dihapus sudah benar...!!!!");
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SKPCariPenilaianPegawai dialog = new SKPCariPenilaianPegawai(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.CekBox ChkInput;
    private widget.panelisi FormInput;
    private widget.TextBox KdDInilai;
    private widget.TextBox KdKategori;
    private widget.TextBox KdPenilai;
    private widget.Label LTotal;
    private widget.editorpane LoadHTML2;
    private widget.TextBox NmDinilai;
    private widget.TextBox NmKategori;
    private widget.TextBox NmPenilai;
    private widget.TextBox NoPenilaian;
    private javax.swing.JPanel PanelInput;
    private widget.ComboBox Sasaran;
    private widget.ComboBox Status;
    private widget.TextBox TCari;
    private widget.Tanggal Tanggal1;
    private widget.Tanggal Tanggal2;
    private widget.Button btnDinilai;
    private widget.Button btnKategori;
    private widget.Button btnPenilai;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label15;
    private widget.Label label17;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        try {
            htmlContent = new StringBuilder();
            htmlContent.append(
                "<tr class='head'>"+
                    "<td valign='top' bgcolor='#FFFAFA' align='center' width='95px'>No.Penilaian</td>"+
                    "<td valign='top' bgcolor='#FFFAFA' align='center' width='200px'>Yang Dinilai</td>"+
                    "<td valign='top' bgcolor='#FFFAFA' align='center' width='200px'>Yang Menilai</td>"+
                    "<td valign='top' bgcolor='#FFFAFA' align='center' width='105px'>Tanggal</td>"+
                    "<td valign='top' bgcolor='#FFFAFA' align='center' width='300px'>Keterangan</td>"+
                    "<td valign='top' bgcolor='#FFFAFA' align='center' width='100px'>Status</td>"+
                "</tr>");
            ps=koneksi.prepareStatement(
                    "select skp_penilaian.nomor_penilaian,skp_penilaian.nik_dinilai,dinilai.nama as dinilai,skp_penilaian.nik_penilai,penilai.nama as penilai,skp_penilaian.tanggal,skp_penilaian.keterangan,"+
                    "skp_penilaian.status from skp_penilaian inner join pegawai as dinilai on skp_penilaian.nik_dinilai=dinilai.nik inner join pegawai as penilai on skp_penilaian.nik_penilai=penilai.nik "+
                    "where skp_penilaian.tanggal between '"+Valid.SetTgl(Tanggal1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(Tanggal2.getSelectedItem()+"")+" 23:59:59' "+
                    (!Status.getSelectedItem().toString().equals("Semua")?"and skp_penilaian.status='"+Status.getSelectedItem().toString()+"' ":"")+
                    (!KdPenilai.getText().equals("")?"and skp_penilaian.nik_penilai='"+KdPenilai.getText()+"' ":"")+
                    (!KdDInilai.getText().equals("")?"and skp_penilaian.nik_dinilai='"+KdDInilai.getText()+"' ":"")+
                    (!NoPenilaian.getText().equals("")?"and skp_penilaian.nomor_penilaian='"+NoPenilaian.getText()+"' ":"")+" order by skp_penilaian.tanggal");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    htmlContent.append(
                        "<tr class='isi'>"+
                            "<td valign='top' align='center' rowspan='2'>"+rs.getString("nomor_penilaian")+"</td>"+
                            "<td valign='top' align='left'>"+rs.getString("nik_dinilai")+" "+rs.getString("dinilai")+"</td>"+
                            "<td valign='top' align='left'>"+rs.getString("nik_penilai")+" "+rs.getString("penilai")+"</td>"+
                            "<td valign='top' align='center'>"+rs.getString("tanggal")+"</td>"+
                            "<td valign='top' align='left'>"+rs.getString("keterangan")+"</td>"+
                            "<td valign='top' align='center'>"+rs.getString("status")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+
                            "<td valign='top' align='center' colspan='5'>"+
                               "<table width='100%' border='0' align='left' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                   "<tr class='isi'>"+
                                        "<td valign='top' bgcolor='#FFFAFA' align='center' width='8%'>Kode</td>"+
                                        "<td valign='top' bgcolor='#FFFAFA' align='center' width='37%'>Kriteria</td>"+
                                        "<td valign='top' bgcolor='#FFFAFA' align='center' width='4%'>Skala</td>"+
                                        "<td valign='top' bgcolor='#FFFAFA' align='center' width='26%'>Kategori</td>"+
                                        "<td valign='top' bgcolor='#FFFAFA' align='center' width='25%'>Sasaran</td>"+
                                   "</tr>"
                    );
                    ps2=koneksi.prepareStatement(
                            "select skp_detail_penilaian.kode_kriteria,skp_kriteria_penilaian.nama_kriteria,skp_detail_penilaian.skala_penilaian,skp_kategori_penilaian.nama_kategori,"+
                            "skp_kategori_penilaian.sasaran from skp_detail_penilaian inner join skp_kriteria_penilaian on skp_kriteria_penilaian.kode_kriteria=skp_detail_penilaian.kode_kriteria "+
                            "inner join skp_kategori_penilaian on skp_kategori_penilaian.kode_kategori=skp_kriteria_penilaian.kode_kategori where skp_detail_penilaian.nomor_penilaian='"+rs.getString("nomor_penilaian")+"' "+
                            (!Sasaran.getSelectedItem().toString().equals("Semua")?"and skp_kategori_penilaian.sasaran='"+Sasaran.getSelectedItem().toString().substring(0,1)+"' ":"")+
                            (!KdKategori.getText().equals("")?"and skp_kategori_penilaian.kode_kategori='"+KdKategori.getText()+"' ":"")+(TCari.getText().trim().equals("")?"":" and "+
                            "(skp_detail_penilaian.kode_kriteria like '%"+TCari.getText()+"%' or skp_kriteria_penilaian.nama_kriteria like '%"+TCari.getText()+"%')")+" order by skp_kategori_penilaian.sasaran");
                    try {
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            htmlContent.append(
                                "<tr class='isi'>"+
                                    "<td valign='top' align='center'>"+rs2.getString("kode_kriteria")+"</td>"+
                                    "<td valign='top'>"+rs2.getString("nama_kriteria")+"</td>"+
                                    "<td valign='top' align='center'>"+rs2.getString("skala_penilaian")+"</td>"+
                                    "<td valign='top'>"+rs2.getString("nama_kategori")+"</td>"+
                                    "<td valign='top'>"+
                                        rs2.getString("sasaran").replaceAll("1","1. Mengidentifikasi Pasien Dengan Benar").
                                        replaceAll("2","2. Meningkatkan Komunikasi Yang Efektif").
                                        replaceAll("3","3. Meningkatkan Keamanan Obat-obatan Yang Harus Diwaspadai").
                                        replaceAll("4","4. Memastikan Lokasi Pembedahan Yang Benar, Prosedur Yang Benar, Pembedahan Pada Pasien Yang Benar").
                                        replaceAll("5","5. Mengurangi Risiko Infeksi Akibat Perawatan Kesehatan").
                                        replaceAll("6","6. Mengurangi Risiko Cidera Pasien Akibat Terjatuh")+
                                    "</td>"+
                               "</tr>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    htmlContent.append(
                               "</table>"+
                            "</td>"+
                        "</tr>"
                    );  

                }
                LTotal.setText(rs.getRow()+"");
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            LoadHTML2.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='left' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }

    
    public void isCek(){
        TCari.requestFocus();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,96));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
}
