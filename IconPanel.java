import iconfinder.IconFinderImageData;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class IconPanel extends JPanel implements ActionListener
{
    JPanel buttonPanel;
    ImagePanel imagePanel;
    JButton download;
    JFileChooser fileChooser;
    private IconFinderImageData ifid;

    IconPanel(IconFinderImageData ifid) {
        super(new BorderLayout());
        
        if (ifid != null) {
            imagePanel = new ImagePanel(ifid.url);
            fileChooser = new JFileChooser();

            buttonPanel = new JPanel(new GridLayout(1, 2));
            download = new JButton("download");
            download.addActionListener(this);

            buttonPanel.add(download);
            add(buttonPanel, BorderLayout.SOUTH);
            add(imagePanel, BorderLayout.CENTER);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == download) {
            try {
                fileChooser.setSelectedFile(new File(imagePanel.getUrl().toString()));
                fileChooser.showSaveDialog(this.getParent());
                InputStream in = imagePanel.getUrl().openStream();
                OutputStream out = new BufferedOutputStream(new FileOutputStream(fileChooser.getSelectedFile()));

                for (int b; (b = in.read()) != -1;) {
                    out.write(b);
                }

                out.close();
                in.close();
            } catch (Exception ex) {
            }
        }
    }
}
