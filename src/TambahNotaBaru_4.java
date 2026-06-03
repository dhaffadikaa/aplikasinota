/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author DHAFFA
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author DHAFFA
 */
public class TambahNotaBaru_4 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TambahNotaBaru_4.class.getName());

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=DbNotaku;encrypt=true;trustServerCertificate=true;";
    private final String userDb = "Test1";
    private final String passDb = "123";
    private int loggedInUserId;

    /**
     * Creates new form TambahNotaBaru_4
     */
    public TambahNotaBaru_4(int idUser) {
        this.loggedInUserId = idUser; 
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(600, 490);
        NomerNotaTF.setText(generateNoNota());
    }
    
    public TambahNotaBaru_4() {
        initComponents();
        this.setLocationRelativeTo(null); 
        
        NomerNotaTF.setText(generateNoNota());
    }

    
    private String generateNoNota() {
        String newNo = "NOTA-001"; 
        
        String sql = "SELECT MAX(no_nota) AS last_nota FROM Nota";
        
        try (Connection conn = DriverManager.getConnection(url, userDb, passDb);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
             
            if (rs.next() && rs.getString("last_nota") != null) {
                String lastNo = rs.getString("last_nota");
                try {
                    int noUrut = Integer.parseInt(lastNo.substring(5)) + 1;

                    newNo = String.format("NOTA-%03d", noUrut);
                } catch (NumberFormatException e) {
                    System.out.println("Format nota sebelumnya tidak sesuai standar.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal membuat nomor nota otomatis: " + e.getMessage(), "Error Database", JOptionPane.ERROR_MESSAGE);
        }
        
        return newNo;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NomerNotaTF = new javax.swing.JTextField();
        NamaTokoTF = new javax.swing.JTextField();
        NamaTokolbl = new javax.swing.JLabel();
        Judul = new javax.swing.JLabel();
        AlamatTF = new javax.swing.JTextField();
        Alamatlbl = new javax.swing.JLabel();
        NomerNotalbl = new javax.swing.JLabel();
        TanggalTF = new javax.swing.JTextField();
        Tanggallbl = new javax.swing.JLabel();
        KembaliButton = new javax.swing.JButton();
        KonfirmasiButton = new javax.swing.JButton();
        BackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NomerNotaTF.setEditable(false);
        NomerNotaTF.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        NomerNotaTF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        NomerNotaTF.addActionListener(this::NomerNotaTFActionPerformed);
        getContentPane().add(NomerNotaTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 126, 290, 30));

        NamaTokoTF.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        NamaTokoTF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        NamaTokoTF.addActionListener(this::NamaTokoTFActionPerformed);
        getContentPane().add(NamaTokoTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 290, 30));

        NamaTokolbl.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        NamaTokolbl.setText("NAMA TOKO");
        getContentPane().add(NamaTokolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 195, -1, 20));

        Judul.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 20)); // NOI18N
        Judul.setText("INFORMASI NOTA");
        getContentPane().add(Judul, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, 20));

        AlamatTF.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        AlamatTF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        AlamatTF.addActionListener(this::AlamatTFActionPerformed);
        getContentPane().add(AlamatTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 290, 30));

        Alamatlbl.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        Alamatlbl.setText("ALAMAT");
        getContentPane().add(Alamatlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 255, -1, 20));

        NomerNotalbl.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        NomerNotalbl.setText("NOMER NOTA");
        getContentPane().add(NomerNotalbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, 20));

        TanggalTF.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        TanggalTF.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        TanggalTF.addActionListener(this::TanggalTFActionPerformed);
        getContentPane().add(TanggalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, 290, 30));

        Tanggallbl.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        Tanggallbl.setText("TANGGAL");
        getContentPane().add(Tanggallbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 315, -1, 20));

        KembaliButton.setBackground(new java.awt.Color(255, 102, 102));
        KembaliButton.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        KembaliButton.setText("KEMBALI");
        KembaliButton.addActionListener(this::KembaliButtonActionPerformed);
        getContentPane().add(KembaliButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, 100, 30));

        KonfirmasiButton.setBackground(new java.awt.Color(153, 255, 153));
        KonfirmasiButton.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        KonfirmasiButton.setText("KONFIRMASI");
        KonfirmasiButton.addActionListener(this::KonfirmasiButtonActionPerformed);
        getContentPane().add(KonfirmasiButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 100, 30));

        BackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundTambahNota.png"))); // NOI18N
        getContentPane().add(BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KonfirmasiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KonfirmasiButtonActionPerformed
        String noNota = NomerNotaTF.getText(); 
    String namaToko = NamaTokoTF.getText();
    String alamat = AlamatTF.getText();
    String tanggal = TanggalTF.getText(); 

    if (noNota.isEmpty() || namaToko.isEmpty() || tanggal.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom (kecuali alamat mungkin) wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String sql = "INSERT INTO Nota (no_nota, nama_toko, alamat, tanggal, id_user) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(url, userDb, passDb);
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, noNota);
        ps.setString(2, namaToko);
        ps.setString(3, alamat);
        ps.setString(4, tanggal);
        ps.setInt(5, this.loggedInUserId); 
        
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Nota baru berhasil disimpan!");
        
        ListNota_1 kembali = new ListNota_1(this.loggedInUserId);
        kembali.setVisible(true);
        this.dispose();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menyimpan nota: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_KonfirmasiButtonActionPerformed

    private void KembaliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KembaliButtonActionPerformed
       ListNota_1 halamanUtama = new ListNota_1(this.loggedInUserId);
        halamanUtama.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_KembaliButtonActionPerformed

    private void NomerNotaTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomerNotaTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomerNotaTFActionPerformed

    private void NamaTokoTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaTokoTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaTokoTFActionPerformed

    private void AlamatTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlamatTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatTFActionPerformed

    private void TanggalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalTFActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> new TambahNotaBaru_4().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AlamatTF;
    private javax.swing.JLabel Alamatlbl;
    private javax.swing.JLabel BackGround;
    private javax.swing.JLabel Judul;
    private javax.swing.JButton KembaliButton;
    private javax.swing.JButton KonfirmasiButton;
    private javax.swing.JTextField NamaTokoTF;
    private javax.swing.JLabel NamaTokolbl;
    private javax.swing.JTextField NomerNotaTF;
    private javax.swing.JLabel NomerNotalbl;
    private javax.swing.JTextField TanggalTF;
    private javax.swing.JLabel Tanggallbl;
    // End of variables declaration//GEN-END:variables
}
