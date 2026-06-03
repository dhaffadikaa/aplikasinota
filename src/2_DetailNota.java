import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;

class DetailNota extends JDialog {

    private static final NumberFormat FMT = NumberFormat.getInstance(new Locale("id", "ID"));

    public DetailNota(Frame parent, String noNota, String namaToko, Object[][] data) {
        super(parent, "Detail Nota", true);

        setSize(520, 380);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout(8, 0));
        header.setBackground(new Color(235, 240, 250));
        header.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBackground(new Color(30, 90, 160));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(e -> JOptionPane.showMessageDialog(this, "Mode update diaktifkan."));

        JLabel lblTitle = new JLabel(noNota + "  /  " + namaToko, SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));

        header.add(btnUpdate, BorderLayout.WEST);
        header.add(lblTitle,  BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // Tabel
        String[] cols = {"Jumlah", "Nama Barang", "Harga Satuan", "Total"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        long subtotal = 0;
        for (Object[] row : data) {
            int  qty   = (int)  row[0];
            long harga = (long) row[2];
            long total = qty * harga;
            subtotal  += total;
            model.addRow(new Object[]{ qty, row[1], "Rp " + FMT.format(harga), "Rp " + FMT.format(total) });
        }

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setGridColor(new Color(220, 220, 220));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int c : new int[]{0, 2, 3}) table.getColumnModel().getColumn(c).setCellRenderer(right);
        table.getColumnModel().getColumn(0).setPreferredWidth(55);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        add(new JScrollPane(table), BorderLayout.CENTER);

        // Footer subtotal
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 10));
        footer.setBackground(new Color(245, 247, 252));
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 225)));
        footer.add(new JLabel("Subtotal :"));
        JLabel lblSub = new JLabel("Rp " + FMT.format(subtotal));
        lblSub.setFont(new Font("SansSerif", Font.BOLD, 15));
        lblSub.setForeground(new Color(30, 90, 160));
        footer.add(lblSub);
        add(footer, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Object[][] data = {
                {3,  "Beras Premium", 15000L},
                {5,  "Minyak Goreng", 14000L},
                {2,  "Gula Pasir",    13000L},
                {10, "Mie Instan",     3500L},
            };
            DetailNota d = new DetailNota(null, "NT-001", "Toko Makmur", data);
            d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            d.setVisible(true);
            System.exit(0);
        });
    }
}