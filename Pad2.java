import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.*;

import java.awt.*;  
import javax.swing.JFrame;  
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.RenderingHints;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.awt.event.*;
import java.util.*;
public class Pad2 extends Canvas implements ActionListener
{
	JFrame f;
	JTabbedPane tb;
	JComponent c1;
	JComponent gen;
	JTextArea a;
	JScrollPane jsp;
  int counter=1;
	JMenuBar mb;
	JMenu file,search,settings;
	JMenuItem open,New,saveas;

 Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("cover5.jpg");  

	public Pad2()
	{
		f=new JFrame("pane");

		tb=new JTabbedPane();
		c1=makeTextPanel();
		tb.setUI(new CustomTabbedPaneUI());
		//c1.add(a1);
		tb.setTabPlacement(SwingConstants.TOP);
		tb.addTab("tab1",c1);
		//c2=makeTextPanel();
		//a2=new JTextArea(20,20);
		//c2.add(a2);
		//tb.addTab("tab2",c2);

         mb=new JMenuBar();
         file=new JMenu("file");
		 search=new JMenu("search");
	     settings=new JMenu("settings");
		 open=new JMenuItem("open");
		 New=new JMenuItem("new");
		 New.addActionListener(this);
		 saveas=new JMenuItem("Save as");
		 saveas.addActionListener(this);
		 file.add(saveas);
		 file.add(open);
		 file.add(New);
	
		file.setMnemonic(KeyEvent.VK_F);
		search.setMnemonic(KeyEvent.VK_S);
		settings.setMnemonic(KeyEvent.VK_T);
		
		mb.add(file);
		mb.add(search);
		mb.add(settings);
		f.setJMenuBar(mb);

		f.add(tb);

		f.setSize(800,800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
			}
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==saveas)
			{
				//a.setText("save");
				
				CreateTextField ct=new CreateTextField();
				return;

			}
          gen=makeTextPanel();
          counter++;
          //tb.addTab("tab"+counter,gen); 
         // tb.addTab("  ",gen);// lab.setPreferredSize(new Dimension(200, 30));

      // Graphics g=new Graphics();
      //      Toolkit t=Toolkit.getDefaultToolkit();  
      //   Image i=t.getImage("cover5.jpg");  
         //g.drawImage(i,0,0,50,50,this);  
           // tb.setIconAt(counter,(Icon)i);

 tb.add(new JLayer<JTabbedPane>(tb, new CloseableTabbedPaneLayerUI()));

          // tb.addTab(tabCreate(),gen);
		}
		public void paint(Graphics g)
		{
           g.drawImage(i,0,0,50,50,this); 
		}

		// public String tabCreate()
		// {
            
		// }

protected JComponent makeTextPanel() 
{
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel();
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(filler);panel.setBorder(new TitledBorder(new EtchedBorder()));
    a=new JTextArea(20,20);

    Font font = new Font("Serif", Font.PLAIN, 20);
        a.setFont(font);

   jsp= new JScrollPane(a);
    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    // panel.add(a);
    panel.add(jsp);
   // setBackgroundAt(counter-1,
                           // Color.red);
    return panel;
}
	public static void main(String s[])
	{
		new Pad2();
	}

	//second class to create dialog box
	class CreateTextField implements ActionListener
	{
		JFrame f2;
		JTextField tf;
		JButton b,cancel;JLabel l;
        FileWriter fw;
		public CreateTextField()
		{
			f2=new JFrame("save as");
			tf=new JTextField(10);
            tf.setBounds(150,40,200,30);
			b=new JButton("save");
			b.addActionListener(this);
			b.setBounds(100,300,100,20);
			cancel=new JButton("Cancel");
			cancel.addActionListener(this);
			cancel.setBounds(250,300,100,20);
			l=new JLabel();
			l.setText("Enter file name");
			l.setBounds(5,40,120,20);
			f2.add(l);
			f2.add(tf);
			f2.add(b);f2.add(cancel);
			f2.setSize(400,400);
			f2.setLayout(null);
			f2.setVisible(true);


		}
		public void actionPerformed(ActionEvent ae)
		{
		//	String x=tf.getText();
			if(ae.getSource()==b)
			{
          try{ fw=new FileWriter(tf.getText());
            fw.write(a.getText());
            fw.close();
            f2.setVisible(false);
        }catch(Exception e)
        {
        	System.out.println(e);
        }
		}
		else if(ae.getSource()==cancel)
		 {
			f2.setVisible(false);
		 }
	    }
}
}

class CloseableTabbedPaneLayerUI extends LayerUI<JTabbedPane> {
  private final JPanel p = new JPanel();
  private final Point pt = new Point(-100, -100);
  private final JButton button = new JButton("x") {
    @Override public Dimension getPreferredSize() {
      return new Dimension(16, 16);
    }
  };
  public CloseableTabbedPaneLayerUI() {
    super();
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setContentAreaFilled(false);
    button.setRolloverEnabled(false);
  }
  @Override public void paint(Graphics g, JComponent c) {
    super.paint(g, c);
    if (c instanceof JLayer) {
      JLayer jlayer = (JLayer) c;
      JTabbedPane tabPane = (JTabbedPane) jlayer.getView();
      for (int i = 0; i < tabPane.getTabCount(); i++) {
        Rectangle rect = tabPane.getBoundsAt(i);
        Dimension d = button.getPreferredSize();
        int x = rect.x + rect.width - d.width - 2;
        int y = rect.y + (rect.height - d.height) / 2;
        Rectangle r = new Rectangle(x, y, d.width, d.height);
        button.setForeground(r.contains(pt) ? Color.RED : Color.BLACK);
        SwingUtilities.paintComponent(g, button, p, r);
      }
    }
  }
  @Override public void installUI(JComponent c) {
    super.installUI(c);
    ((JLayer)c).setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
  }
  @Override public void uninstallUI(JComponent c) {
    ((JLayer)c).setLayerEventMask(0);
    super.uninstallUI(c);
  }
  @Override protected void processMouseEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
    if (e.getID() == MouseEvent.MOUSE_CLICKED) {
      pt.setLocation(e.getPoint());
      JTabbedPane tabbedPane = (JTabbedPane) l.getView();
      int index = tabbedPane.indexAtLocation(pt.x, pt.y);
      if (index >= 0) {
        Rectangle rect = tabbedPane.getBoundsAt(index);
        Dimension d = button.getPreferredSize();
        int x = rect.x + rect.width - d.width - 2;
        int y = rect.y + (rect.height - d.height) / 2;
        Rectangle r = new Rectangle(x, y, d.width, d.height);
        if (r.contains(pt)) {
          tabbedPane.removeTabAt(index);
        }
      }
      l.getView().repaint();
    }
  }
  @Override protected void processMouseMotionEvent(MouseEvent e, JLayer<? extends JTabbedPane> l) {
    pt.setLocation(e.getPoint());
    JTabbedPane tabbedPane = (JTabbedPane) l.getView();
    int index = tabbedPane.indexAtLocation(pt.x, pt.y);
    if (index >= 0) {
      tabbedPane.repaint(tabbedPane.getBoundsAt(index));
    } else {
      tabbedPane.repaint();
    }
  }
}

// class CustomTabbedPaneUI extends MetalTabbedPaneUI
// {
//    Rectangle xRect;
  
//    protected void installListeners() {
//       super.installListeners();
//       tabPane.addMouseListener(new MyMouseHandler());
//    }
  
//    protected void paintTab(Graphics g, int tabPlacement,
//                            Rectangle[] rects, int tabIndex,
//                            Rectangle iconRect, Rectangle textRect) {
//       super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
  
//       Font f = g.getFont();
//       g.setFont(new Font("Courier", Font.BOLD, 10));
//       FontMetrics fm = g.getFontMetrics(g.getFont());
//       int charWidth = fm.charWidth('x');
//       int maxAscent = fm.getMaxAscent();
//       g.drawString("x", textRect.x + textRect.width -3, textRect.y + textRect.height - 3);  //changed 
//       g.drawRect(textRect.x+textRect.width-5,
//                  textRect.y+textRect.height-maxAscent, charWidth+2, maxAscent-1);
//       xRect = new Rectangle(textRect.x+textRect.width-5,
//                  textRect.y+textRect.height-maxAscent, charWidth+2, maxAscent-1);
//       g.setFont(f);
//     }
  
//     public class MyMouseHandler extends MouseAdapter {
//         public void mousePressed(MouseEvent e) {
//             // System.out.println(e);
//             if (xRect.contains(e.getPoint())) {
//                JTabbedPane tabPane = (JTabbedPane)e.getSource();
//                int tabIndex = tabForCoordinate(tabPane, e.getX(), e.getY());
//                tabPane.remove(tabIndex);
//             }
//         }
//     }

