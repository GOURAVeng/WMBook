import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	public Main() throws Exception {
		super("WordMeaningBook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		// create menu
		JMenu menuWordMeaningBook = new JMenu("WordMeaningBook");
		mb.add(menuWordMeaningBook);
		
		// options in WordMeaningBook Menu
		JMenuItem option = new JMenuItem("Insert");
		option.setIcon(getImage("insert.png"));
		option.setAccelerator(KeyStroke.getKeyStroke("F3"));
		menuWordMeaningBook.add(option);
		option.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addWord();
			}
		});
		
		// options in WordMeaningBook Menu
        option = new JMenuItem("Remove");
        option.setIcon( getImage("remove.png"));
        option.setAccelerator( KeyStroke.getKeyStroke("F4"));
        menuWordMeaningBook.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        menuWordMeaningBook.addSeparator();

        // options in WordMeaningBook Menu
        option = new JMenuItem("Find");
        option.setIcon( getImage("find.png"));
        option.setAccelerator( KeyStroke.getKeyStroke("F5"));
        menuWordMeaningBook.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });


        option = new JMenuItem("View");
        option.setIcon( getImage("view.png"));
        option.setAccelerator( KeyStroke.getKeyStroke("F6"));
        menuWordMeaningBook.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listWords();
            }
        });

        menuWordMeaningBook.addSeparator();

        option = new JMenuItem("Exit");
        menuWordMeaningBook.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });

        addStorageMenu(mb);
        addToolbar();
        setJMenuBar(mb);

        // load WordMeaningBook from disk
        WordMeaningBook.loadFromDisk();

    }

    public void exit() {
        if (WordMeaningBook.isModified()) {
            int option = JOptionPane.showConfirmDialog(Main.this, "Do you want to save changes?",
                    "Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {  // exit after save
                WordMeaningBook.saveToDisk();
                System.exit(0);
            }
            else if (option == JOptionPane.NO_OPTION) // exit without saving
            {
               System.exit(0);
            }
        // if cancel then do not exit
        } else {
            System.exit(0);
        }
    }

    public ImageIcon getImage(String filename){
    	try {
        return new ImageIcon(
                this.getClass().getResource(filename));
    	}
    	catch(Exception ex) {
    		System.out.println( ex.getMessage());
    		return null;
    	}
    }

    public void centerToParent(JFrame parent, JFrame child) {
        Dimension pd = parent.getSize();
        Dimension cd = child.getSize();
        int x = (int) (pd.getWidth() - cd.getWidth()) / 2;
        int y = (int) (pd.getHeight() - cd.getHeight()) / 2;
        child.setLocation(x, y);

    }

    public void addWord() {
        Insert w = new Insert();
        centerToParent(Main.this, w);
        w.setVisible(true);
    }

    public void deleteWord() {
        DeleteWord w = new DeleteWord();
        centerToParent(Main.this, w);
        w.setVisible(true);
    }

    public void searchWord() {
        find w = new find();
        centerToParent(Main.this, w);
        w.setVisible(true);
    }

    public void listWords() {
        ListWords w = new ListWords();
        w.setVisible(true);
        centerToParent(Main.this, w);
    }

    public void addToolbar() {
        JToolBar tb = new JToolBar();
        JButton b = new JButton( getImage("insert.png"));
        b.setLocation(100, 100);
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Insert");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 addWord();
            }

        });

        b = new JButton( getImage("remove.png"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Remove");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 deleteWord();
            }

        });

        b = new JButton( getImage("find.png"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("Find Word");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 searchWord();
            }

        });


        b = new JButton( getImage("view.png"));
        b.setPreferredSize( new Dimension(32,32));
        tb.add(b);
        b.setToolTipText("View All Words");
        b.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 listWords();
            }

        });
        
        JLabel lblNewLabel = new JLabel("Save for successful Updation of WMBook");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setToolTipText("INFO");
		lblNewLabel.setVisible(true);
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		
		tb.setMargin(new Insets(5, 500, 5, 5));
        getContentPane().add(tb, BorderLayout.CENTER);   // NORTH before
    }
    //
    public void addStorageMenu(JMenuBar mb) {

        JMenu menuStorage = new JMenu("Storage");

        // options in Storage Menu
        JMenuItem option = new JMenuItem("Save WordMeaningBook");
        option.setIcon( getImage("save.png"));
        option.setAccelerator( KeyStroke.getKeyStroke("F1"));
        menuStorage.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = WordMeaningBook.saveToDisk();
                if (result) {
                    JOptionPane.showMessageDialog(Main.this, "Saved WordMeaningBook Successfully!", "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Could Not Save WordMeaningBook Successfully! Error --> " + WordMeaningBook.getMessage(), "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        option = new JMenuItem("Load WordMeaningBook");
        option.setIcon( getImage("load.png"));
        option.setAccelerator( KeyStroke.getKeyStroke("F2"));
        menuStorage.add(option);
        option.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = WordMeaningBook.loadFromDisk();
                if (result) {
                    JOptionPane.showMessageDialog(Main.this, "Loaded WordMeaningBook Successfully!", "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Error in Loading --> " + WordMeaningBook.getMessage(), "Feedback",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        mb.add(menuStorage);

    }

    public static void main(String[] args) throws Exception {
        Main f = new Main();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
