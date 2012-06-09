import iconfinder.ApiQuery;
import iconfinder.IconFinderApiServer;
import iconfinder.IconFinderImageData;
import iconfinder.ServerError;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class IconFinder implements KeyListener, ActionListener, ItemListener {
	JFrame appFrame;
	JPanel mainPanel;
	JPanel topPanel;
	JPanel contentPanel;
	JPanel buttonPanel;
	JComboBox licenseBox;
	JButton next, prev;
	JTextField sField;
	// -----------------------

	public final static int PER_PAGE = 12;
	private final IconFinderApiServer apiService;
	private ApiQuery apiQuery = null;

	public IconFinder(IconFinderApiServer apiService) {
		this.apiService = apiService;

		appFrame = new JFrame("IconFinder");
		mainPanel = new JPanel(new BorderLayout());
		topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		contentPanel = new JPanel(new GridLayout(PER_PAGE / 4, 4, 5, 5));
		buttonPanel = new JPanel(new GridLayout(1, 2));
		licenseBox = new JComboBox(new String[] { "No license filtering", "For commercial use" });
		next = new JButton("NEXT");
		prev = new JButton("PREV");
		sField = new JTextField();

		buildLayout();
		bindListeners();
	}

	public void buildLayout() {

		sField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 10));

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(contentPanel, BorderLayout.CENTER);

		buttonPanel.add(prev);
		buttonPanel.add(next);

		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 6));

		licenseBox.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

		topPanel.setPreferredSize(new Dimension(500, 50));
		topPanel.add(sField);
		topPanel.add(licenseBox);

		appFrame.add(mainPanel);
		appFrame.setResizable(false);
		appFrame.setPreferredSize(new Dimension(500, 500));
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setVisible(true);
		appFrame.pack();
		appFrame.setLocationRelativeTo(null);
	}

	public void bindListeners() {
        sField.addKeyListener(this);
        prev.addActionListener(this);
        next.addActionListener(this);
        licenseBox.addItemListener(this);
    }

	public int getLicense() {
		return licenseBox.getSelectedIndex();
	}

	public void updateContent(IconFinderImageData[] data) {
		if (data != null) {
			contentPanel.removeAll();

			int tmp = data.length;

			for (IconFinderImageData ifid : data)
				contentPanel.add(new IconPanel(ifid));

			for (int i = tmp; i < PER_PAGE; i++)
				contentPanel.add(new IconPanel(null));

			contentPanel.validate();
		}
	}

	// ----KeyListener----
	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				updateContent(apiService.execute(apiQuery = new ApiQuery(sField.getText(), getLicense(), 0, PER_PAGE)));
			} catch (ServerError e1) {
				JOptionPane.showMessageDialog(appFrame, "Server error");
			}
		}
	}

	// ----ActionListener----
	public void actionPerformed(ActionEvent e) {
		if (apiQuery != null) {
			IconFinderImageData[] data = null;
			try {
				if (e.getSource() == next) {
					apiQuery.page += 1;
					data = apiService.execute(apiQuery);
				}

				if (e.getSource() == prev) {
					if (apiQuery.page >= 1) {
						apiQuery.page -= 1;
						data = apiService.execute(apiQuery);
					}
				}
				updateContent(data);

			} catch (ServerError e1) {
				JOptionPane.showMessageDialog(appFrame, "Server error");
			}
		}

	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		try {
			updateContent(apiService.execute(apiQuery = new ApiQuery(sField.getText(), getLicense(), 0, PER_PAGE)));
		} catch (ServerError e) {
			JOptionPane.showMessageDialog(appFrame, "Server error");
		}

	}
}
