/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author DHAFFA
 */
public class ListNota_1 extends javax.swing.JFrame {
    
    private int loggedInUserId;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ListNota_1.class.getName());
    
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=DbNotaku;encrypt=true;trustServerCertificate=true;";
    private final String userDb = "Test1";
    private final String passDb = "123";

    public ListNota_1(int idUser) {
        this.loggedInUserId = idUser; 
        setupHalamanUtama();
        this.setResizable(false);
    }

    
    public ListNota_1() {
        setupHalamanUtama();
        this.setResizable(false);
    }
    
    private void setupHalamanUtama() {
        initComponents();
 
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] { "No Nota", "Nama Toko", "Alamat", "Tanggal" }
            ) {
                Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 2) { 
                        int selectedRow = jTable1.getSelectedRow();
                        if (selectedRow != -1) {
                            String noNota = jTable1.getValueAt(selectedRow, 0).toString();
                            
                            DetailNota_2 halamanDetail = new DetailNota_2(noNota, ListNota_1.this.loggedInUserId);
                            halamanDetail.setVisible(true);
                            
                            dispose();
                        }
                    }
                }
            });
            
        loadData(); 
        this.setSize(600, 490);
        this.setLocationRelativeTo(null);
    }
    
    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); 

    String query = "SELECT n.no_nota, n.nama_toko, n.alamat, n.tanggal, " +"ISNULL((SELECT SUM(d.total_harga) FROM DetailNota d WHERE d.no_nota = n.no_nota), 0) AS total_harga " +"FROM Nota n WHERE n.id_user = ?";
    long totalSemuaNota = 0; 

    try (Connection conn = DriverManager.getConnection(url, userDb, passDb);
         PreparedStatement ps = conn.prepareStatement(query)) {
         
        ps.setInt(1, this.loggedInUserId); 
        
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String noNota = rs.getString("no_nota");
                String namaToko = rs.getString("nama_toko");
                String alamat = rs.getString("alamat");
                String tanggal = rs.getString("tanggal");
                long totalPerNota = rs.getLong("total_harga"); 
                totalSemuaNota += totalPerNota;

                model.addRow(new Object[]{noNota, namaToko, alamat, tanggal});
            }
        }
        
        SuperTotalTF.setText(String.format("%,d", totalSemuaNota).replace(',', '.'));

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal memuatkan data: " + e.getMessage(), "Ralat", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {                                          
     
        TambahNotaBaru_4 halamanTambah = new TambahNotaBaru_4(this.loggedInUserId); 
        halamanTambah.setVisible(true);
        this.dispose();
    }
    
    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {                                         
        int selectedRow = jTable1.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Silakan pilih baris nota yang ingin dihapus terlebih dahulu!");
            return;
        }

        String noNota = jTable1.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this, 
                "Adakah anda pasti ingin menghapus Nota " + noNota + "?\nSemua detail barang di dalamnya juga akan terhapus.", 
                "Pengesahan Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
          
            String sql = "DELETE FROM Nota WHERE no_nota = ?";

            try (Connection conn = DriverManager.getConnection(url, userDb, passDb);
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                 
                ps.setString(1, noNota); 
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Nota Berhasil Dihapus!");
                loadData(); 
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus nota: " + e.getMessage(), "Ralat", JOptionPane.ERROR_MESSAGE);
            }
        }
    }/**
     * Creates new form ListNota
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TambahNotaBaruBtn = new javax.swing.JButton();
        SuperTotalTF = new javax.swing.JTextField();
        Totallabel = new javax.swing.JLabel();
        LogouttBtn = new javax.swing.JButton();
        HapusNotaBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Background = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 457));
        setMinimumSize(new java.awt.Dimension(600, 457));
        setPreferredSize(new java.awt.Dimension(600, 457));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TambahNotaBaruBtn.setBackground(new java.awt.Color(102, 255, 102));
        TambahNotaBaruBtn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 12)); // NOI18N
        TambahNotaBaruBtn.setForeground(new java.awt.Color(51, 51, 51));
        TambahNotaBaruBtn.setText("TAMBAH NOTA BARU");
        TambahNotaBaruBtn.setToolTipText("");
        TambahNotaBaruBtn.addActionListener(this::TambahNotaBaruBtnActionPerformed);
        getContentPane().add(TambahNotaBaruBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 150, 30));

        SuperTotalTF.setEditable(false);
        SuperTotalTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        SuperTotalTF.addActionListener(this::SuperTotalTFActionPerformed);
        getContentPane().add(SuperTotalTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 415, 120, -1));

        Totallabel.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 12)); // NOI18N
        Totallabel.setText("TOTAL SEMUA NOTA (RP)");
        getContentPane().add(Totallabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, -1, -1));

        LogouttBtn.setBackground(new java.awt.Color(255, 220, 77));
        LogouttBtn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 12)); // NOI18N
        LogouttBtn.setText("LOGOUT");
        LogouttBtn.addActionListener(this::LogouttBtnActionPerformed);
        getContentPane().add(LogouttBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 120, 30));

        HapusNotaBtn.setBackground(new java.awt.Color(255, 87, 87));
        HapusNotaBtn.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 12)); // NOI18N
        HapusNotaBtn.setText("HAPUS NOTA");
        HapusNotaBtn.addActionListener(this::HapusNotaBtnActionPerformed);
        getContentPane().add(HapusNotaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 120, 30));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"asd", "asd", "as", "asd"},
                {"asdadsa", "dasdasd", "asd", null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No Nota", "Nama Toko", "Alamat", "Tanggal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 540, 320));

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackGroundNotaFix.png"))); // NOI18N
        Background.setPreferredSize(new java.awt.Dimension(600, 457));
        getContentPane().add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 460));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TambahNotaBaruBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TambahNotaBaruBtnActionPerformed
        btnTambahActionPerformed(evt);
    }//GEN-LAST:event_TambahNotaBaruBtnActionPerformed

    private void LogouttBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogouttBtnActionPerformed
        new Login_0().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogouttBtnActionPerformed

    private void HapusNotaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusNotaBtnActionPerformed
 
        btnHapusActionPerformed(evt);
    }//GEN-LAST:event_HapusNotaBtnActionPerformed

    private void SuperTotalTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuperTotalTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuperTotalTFActionPerformed

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
            java.util.logging.Logger.getLogger(ListNota_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new ListNota_1().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background;
    private javax.swing.JButton HapusNotaBtn;
    private javax.swing.JButton LogouttBtn;
    private javax.swing.JTextField SuperTotalTF;
    private javax.swing.JButton TambahNotaBaruBtn;
    private javax.swing.JLabel Totallabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
