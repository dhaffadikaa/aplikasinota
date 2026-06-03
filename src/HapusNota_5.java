import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class HapusNota_5 extends javax.swing.JPanel {

    public HapusNota_5() {
        initComponents();
        loadComboBoxData();
    }

    private void loadComboBoxData() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DbNotaku;encrypt=true;trustServerCertificate=true;";
        String user = "Test1";
        String password = "123";
        String query = "SELECT no_nota FROM Nota";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                jComboBoxNoNota.addItem(rs.getString("no_nota"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat daftar No Nota: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelNoNota = new javax.swing.JLabel();
        jComboBoxNoNota = new javax.swing.JComboBox<>();
        jButtonPreview = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPreview = new javax.swing.JTextArea();
        jButtonHapus = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 457));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); 
        jLabelTitle.setText("Hapus Nota");
        add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        jLabelNoNota.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); 
        jLabelNoNota.setText("No Nota");
        add(jLabelNoNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jComboBoxNoNota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Pilih No Nota --" }));
        add(jComboBoxNoNota, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 105, 230, 30));

        jButtonPreview.setBackground(new java.awt.Color(204, 255, 204));
        jButtonPreview.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 12)); 
        jButtonPreview.setText("Preview Nota");
        jButtonPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviewActionPerformed(evt);
            }
        });
        add(jButtonPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 105, 130, 30));

        jTextAreaPreview.setEditable(false);
        jTextAreaPreview.setColumns(20);
        jTextAreaPreview.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPreview);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 460, 200));

        jButtonHapus.setBackground(new java.awt.Color(255, 87, 87));
        jButtonHapus.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); 
        jButtonHapus.setText("HAPUS NOTA");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });
        add(jButtonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 180, 35));
    }

    private void jButtonPreviewActionPerformed(java.awt.event.ActionEvent evt) {
        if (jComboBoxNoNota.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih nomor nota terlebih dahulu!");
            return;
        }

        String selectedNoNota = jComboBoxNoNota.getSelectedItem().toString();
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DbNotaku;encrypt=true;trustServerCertificate=true;";
        String user = "Test1";
        String password = "123";
        String query = "SELECT * FROM Nota WHERE no_nota = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, selectedNoNota);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String sb = "Detail Data Nota:\n" +
                            "=====================\n" +
                            "No Nota      : " + rs.getString("no_nota") + "\n" +
                            "Nama Toko    : " + rs.getString("nama_toko") + "\n" +
                            "Alamat       : " + rs.getString("alamat") + "\n" +
                            "Tanggal      : " + rs.getString("tanggal") + "\n";
                    jTextAreaPreview.setText(sb);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil preview: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (jComboBoxNoNota.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Silakan pilih nomor nota yang ingin dihapus!");
            return;
        }

        String selectedNoNota = jComboBoxNoNota.getSelectedItem().toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus Nota " + selectedNoNota + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DbNotaku;encrypt=true;trustServerCertificate=true;";
            String user = "Test1";
            String password = "123";
            String sql = "DELETE FROM Nota WHERE no_nota = ?";

            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, selectedNoNota);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Nota Berhasil Dihapus!");
                
                jTextAreaPreview.setText("");
                jComboBoxNoNota.removeItem(selectedNoNota);
                jComboBoxNoNota.setSelectedIndex(0);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus nota: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonPreview;
    private javax.swing.JComboBox<String> jComboBoxNoNota;
    private javax.swing.JLabel jLabelNoNota;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPreview;
}