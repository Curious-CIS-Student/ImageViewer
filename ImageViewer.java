import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ImageViewer is the main class of the image viewer application. It builds and
 * displays the application GUI and initialises all other components.
 * 
 * To start the application, create an object of this class.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 0.4
 */
public class ImageViewer
{
    private JFrame frame;
    private ImagePanel imagePanel;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    private OFImage currentImage = null;
    static private double VERSION = 1.0;
    
    /**
     * Create an ImageViewer show it on screen.
     */
    public ImageViewer()
    {
        makeFrame();
    }


    // ---- implementation of menu functions ----

    /**
     * Open function: open a file chooser to select a new image file.
     */
    private void openFile()
    {
        currentImage = ImageFileManager.getImage();
        imagePanel.setImage(currentImage);
        showStatus("Image Loaded");
        frame.pack();
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    /**
     * About function: Display a help menu dialogue with information about the project. (Stub for now)
     */
    private void about()
    {
        JOptionPane.showMessageDialog(frame, "ImageViewer\n" + VERSION,
        "About ImageViewer",
        JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Darker function: Cause the entire image to become darker.
     */
    private void makeDarker()
    {
        if (currentImage != null) {
            currentImage.darker();
            frame.repaint();
            showStatus("Applied: darker");
        }
        else {
           showStatus("No image loaded.");
        }
    }
    
    /**
     * Lighter function: Cause the entire image to become lighter.
     */
    private void makeLighter()
    {
        if (currentImage != null) {
            currentImage.lighter();
            frame.repaint();
            showStatus("Applied: lighter");
        }
        else {
           showStatus("No image loaded.");
        }
    }
    
    /**
     * Threshold function: Cause the image to have each of its pixels set to either white, gray, or black, depending on the color values of its pixels.
     */
    private void threshold()
    {
        if (currentImage != null) {
            currentImage.threshold();
            frame.repaint();
            showStatus("Applied: threshold");
        }
        else {
            showStatus("No image loaded.");
        }
    }
    
    // ---- swing stuff to build the frame and all its components ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("ImageViewer");
        makeMenuBar(frame);
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        filenameLabel = new JLabel("Image filename");
        contentPane.add(filenameLabel, BorderLayout.NORTH);
        
        imagePanel = new ImagePanel();
        contentPane.add(imagePanel, BorderLayout.CENTER);
        
        statusLabel = new JLabel("File Status");
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        // building is done - arrange the components and show        
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem openItem = new JMenuItem("Open");
            openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            openItem.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { openFile(); }
                           });
        fileMenu.add(openItem);

        JMenuItem quitItem = new JMenuItem("Quit");
            quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            quitItem.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        fileMenu.add(quitItem);
        
        createFilterMenu(menubar);
        
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("About ImageViewer...");
            aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, SHORTCUT_MASK));
             aboutItem.addActionListener(new ActionListener() {
                             public void actionPerformed(ActionEvent e) {about(); }
                         });
        helpMenu.add(aboutItem);      
    }
    
    private void createFilterMenu(JMenuBar menubar)
    {
        // create the Filter menu
        JMenu filterMenu = new JMenu("Filter");
        menubar.add(filterMenu);
        
        JMenuItem darkerItem = new JMenuItem("Darker");
        darkerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { makeDarker(); }
        });
        filterMenu.add(darkerItem);
        
        JMenuItem lighterItem = new JMenuItem("Lighter");
        lighterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { makeLighter(); }
        });
        filterMenu.add(lighterItem);
        
        JMenuItem thresholdItem = new JMenuItem("Threshold");
        thresholdItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { threshold(); }
        });
        filterMenu.add(thresholdItem);
    }
    
    private void showStatus(String statusValue)
    {
        statusLabel.setText(statusValue);
        
    }
}
