import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductShopApp extends JFrame {

    private JLabel lblImage, lblName, lblPrice, lblBrand, lblDesc;
    private float alpha = 1.0f;
    private Timer fadeTimer;
    private BufferedImage currentImage;

    public ProductShopApp() {
        setTitle("Giày Adidas Store");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 1. Panel chi tiết bên trái
        JPanel leftPanel = new JPanel() {
            // Hiệu ứng Fade-in
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                if (currentImage != null) {
                    Image scaled = currentImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
                    g2d.drawImage(scaled, 50, 50, null);
                }
            }
        };
        leftPanel.setPreferredSize(new Dimension(400, 600));
        leftPanel.setLayout(null);
        leftPanel.setBackground(Color.WHITE);

        // Khởi tạo các label cho chi tiết
        lblName = new JLabel("Chọn một sản phẩm", SwingConstants.LEFT);
        lblName.setFont(new Font("Arial", Font.BOLD, 20));
        lblName.setBounds(50, 360, 300, 30);
        
        lblPrice = new JLabel("", SwingConstants.LEFT);
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPrice.setBounds(50, 390, 300, 30);

        lblBrand = new JLabel("", SwingConstants.LEFT);
        lblBrand.setBounds(50, 420, 300, 30);
        
        lblDesc = new JLabel("", SwingConstants.LEFT);
        lblDesc.setBounds(50, 450, 300, 30);

        leftPanel.add(lblName);
        leftPanel.add(lblPrice);
        leftPanel.add(lblBrand);
        leftPanel.add(lblDesc);

        // 2. Panel lưới bên phải
        JPanel rightPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        rightPanel.setBackground(new Color(245, 245, 245));
        
        // Thêm dữ liệu mẫu
        String[] imgFiles = {"img1.png", "img2.png", "img3.png", "img4.png", "img5.png", "img6.png"};
        for (int i = 0; i < imgFiles.length; i++) {
            rightPanel.add(new ProductCard(imgFiles[i], "Sản phẩm " + (i + 1), "$160.00", "Adidas", imgFiles[i]));
        }

        add(leftPanel, BorderLayout.WEST);
        add(new JScrollPane(rightPanel), BorderLayout.CENTER);
    }

    // Class quản lý cập nhật và hiệu ứng
    public void updateDetails(String imgPath, String name, String price, String brand) {
        // Reset Alpha
        alpha = 0.0f;
        try {
            this.currentImage = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            System.out.println("Không tìm thấy ảnh: " + imgPath);
        }
        
        lblName.setText(name);
        lblPrice.setText(price);
        lblBrand.setText(brand);
        
        // Chạy Timer cho hiệu ứng mờ dần
        if (fadeTimer != null && fadeTimer.isRunning()) fadeTimer.stop();
        
        fadeTimer = new Timer(30, e -> {
            alpha += 0.05f;
            if (alpha >= 1.0f) {
                alpha = 1.0f;
                fadeTimer.stop();
            }
            repaint(); // Vẽ lại panel bên trái
        });
        fadeTimer.start();
    }

    // Component Thẻ sản phẩm
    class ProductCard extends JPanel {
        public ProductCard(String imgPath, String name, String price, String brand, String fileName) {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            setBackground(Color.WHITE);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            JLabel lbl = new JLabel("Ảnh", SwingConstants.CENTER);
            add(lbl, BorderLayout.CENTER);
            add(new JLabel(name, SwingConstants.CENTER), BorderLayout.SOUTH);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    updateDetails(fileName, name, price, brand);
                }
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductShopApp().setVisible(true));
    }
}
