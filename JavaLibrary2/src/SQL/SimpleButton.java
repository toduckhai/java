package SQL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SimpleButton {
	 public static void main(String[] args) {
	        JFrame frame = new JFrame("Chương trình Java đơn giản");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(300, 150);

	        // Tạo nút "Click"
	        JButton clickButton = new JButton("Click");
	        clickButton.setBounds(50, 30, 80, 30);

	        // Tạo nút "Thoát"
	        JButton exitButton = new JButton("Thoát");
	        exitButton.setBounds(150, 30, 80, 30);

	        // Đăng ký sự kiện cho nút "Click"
	        clickButton.addActionListener(e -> {
	            JOptionPane.showMessageDialog(frame, "Bạn đã nhấn nút 'Click'");
	        });

	        // Đăng ký sự kiện cho nút "Thoát"
	        exitButton.addActionListener(e -> {
	            System.exit(0);
	        });

	        // Thêm các nút vào frame
	        frame.add(clickButton);
	        frame.add(exitButton);

	        // Đặt layout cho frame
	        frame.setLayout(null);

	        // Hiển thị frame
	        frame.setVisible(true);
	    }

}
