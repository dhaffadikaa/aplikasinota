import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TambahNotaBaru_4 extends javax.swing.JPanel {

    public TambahNotaBaru_4() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelNoNota = new javax.swing.JLabel();
        jLabelNamaToko = new javax.swing.JLabel();
        jLabelAlamat = new javax.swing.JLabel();
        jLabelTanggal = new javax.swing.JLabel();
        jTextFieldNoNota = new javax.swing.JTextField();
        jTextFieldNamaToko = new javax.swing.JTextField();
        jTextFieldAlamat = new javax.swing.JTextField();
        jTextFieldTanggal = new javax.swing.JTextField();
        jButtonTambah = new javax.swing.JButton();
        jButtonBatal = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 457));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); 
        jLabelTitle.setText("Tambah Nota Baru");
        add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        jLabelNoNota.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); 
        jLabelNoNota.setText("No Nota :");
        add(jLabelNoNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jLabelNamaToko.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); 
        jLabelNamaToko.setText("Nama Toko :");
        add(jLabelNamaToko, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabelAlamat.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); 
        jLabelAlamat.setText("Alamat :");
        add(jLabelAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        jLabelTanggal.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); 
        jLabelTanggal.setText("Tanggal :");
        add(jLabelTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));
        add(jTextFieldNoNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 280, 30));
        add(jTextFieldNamaToko, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 280, 30));
        add(jTextFieldAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 280, 30));
        add(jTextFieldTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 280, 30));

        jButtonTambah.setBackground(new java.awt.Color(255, 220, 77));
        jButtonTambah.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); 
        jButtonTambah.setText("Tambah Nota Baru");
        jButtonTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTambahActionPerformed(evt);
            }
        });
        add(jButtonTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 180, 35));

        jButtonBatal.setBackground(new java.awt.Color(255, 87, 87));
        jButtonBatal.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); 
        jButtonBatal.setText("Batal");
        jButtonBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBatalActionPerformed(evt);
            }
        });
        add(jButtonBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 90, 35));
    }

    private void jButtonTambahActionPerformed(java.awt.event.ActionEvent evt) {
        String noNota = jTextFieldNoNota.getText();
        String namaToko = jTextFieldNamaToko.getText();
        String alamat = jTextFieldAlamat.getText();
        String tanggal = jTextFieldTanggal.getText();

        if (noNota.isEmpty() || namaToko.isEmpty() || alamat.isEmpty() || tanggal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua data harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String url = "jdbc:sqlserver://localhost:1433;databaseName=DbNotaku;encrypt=true;trustServerCertificate=true;";
        String user = "Test1";
        String password = "123";
        String sql = "INSERT INTO Nota (no_nota, nama_toko, alamat, tanggal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, noNota);
            ps.setString(2, namaToko);
            ps.setString(3, alamat);
            ps.setString(4, tanggal);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Nota baru berhasil disimpan!");
            
            jTextFieldNoNota.setText("");
            jTextFieldNamaToko.setText("");
            jTextFieldAlamat.setText("");
            jTextFieldTanggal.setText("");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonBatalActionPerformed(java.awt.event.ActionEvent evt) {
        jTextFieldNoNota.setText("");
        jTextFieldNamaToko.setText("");
        jTextFieldAlamat.setText("");
        jTextFieldTanggal.setText("");
    }

    private javax.swing.JButton jButtonBatal;
    private javax.swing.JButton jButtonTambah;
    private javax.swing.JLabel jLabelAlamat;
    private javax.swing.JLabel jLabelNamaToko;
    private javax.swing.JLabel jLabelNoNota;
    private javax.swing.JLabel jLabelTanggal;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JTextField jTextFieldAlamat;
    private javax.swing.JTextField jTextFieldNamaToko;
    private javax.swing.JTextField jTextFieldNoNota;
    private javax.swing.JTextField jTextFieldTanggal;
}