import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.*;
import javax.swing.plaf.*;

import java.awt.*;
import java.awt.Color;

import java.awt.event.KeyEvent;
import java.io.*;
import java.awt.event.*;
import java.util.*;
public class Pad implements ActionListener
{
	JFrame f;
	JTabbedPane tb;
	JComponent c1;
	JComponent gen;
	JTextArea a;JScrollPane jsp;
  int counter=1;
	JMenuBar mb;
	JMenu file,search,settings;
	JMenuItem open,New,saveas;
	public Pad()
	{
		f=new JFrame("pane");

		tb=new JTabbedPane();
		c1=makeTextPanel();
		// tb.setUI(new CustomTabbedPaneUI());

		// addWindowListener(new WindowAdapter() {
  //        public void windowClosing(WindowEvent we) {
  //           System.exit(0);
  //        }
  //     });
		
		tb.setTabPlacement(SwingConstants.TOP);
		tb.addTab("tab1",c1);
		
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
			
				
				CreateTextField ct=new CreateTextField();
				return;

			}
          gen=makeTextPanel();
          counter++;
          tb.addTab("tab"+counter,gen); 
		}

protected JComponent makeTextPanel() 
{
    JPanel panel = new JPanel(false);
    JLabel filler = new JLabel();
    filler.setHorizontalAlignment(JLabel.CENTER);
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.add(filler);panel.setBorder(new TitledBorder(new EtchedBorder()));
    a=new JTextArea(20,20);
    a.setBackground(Color.WHITE);
    a.setForeground(Color.BLACK);

    Font font = new Font("Serif", Font.PLAIN, 20);
        a.setFont(font);

   jsp= new JScrollPane(a);
    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


    
    panel.add(jsp);
  
    return panel;
}
	public static void main(String s[])
	{
		new Pad();
	}

	//second class to create dialog box for "Saveas dialog box"
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
			b.setBounds(100,300,100,40);
			cancel=new JButton("Cancel");
			cancel.addActionListener(this);
			cancel.setBounds(250,300,100,40);
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

// class CustomTabbedPaneUI extends MetalTabbedPaneUI //CREATING A  NON-STATIC NESTED CLASS
// {
//    Rectangle xRect;
  
//    protected void installListeners() {
//       super.installListeners();
//       tb.addMouseListener(new MyMouseHandler());
//        // tb.addMouseListener(new MyMouseHandler());
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
//       g.drawString("x", textRect.x + textRect.width - 1, textRect.y + textRect.height - 1);  //changed 
//       g.drawRect(textRect.x+textRect.width-5,
//                  textRect.y+textRect.height-maxAscent, charWidth+2, maxAscent-1);
//       xRect = new Rectangle(textRect.x+textRect.width+10,
//                  textRect.y+textRect.height-maxAscent, charWidth+2, maxAscent-1);
//       g.setFont(f);
//     }
  
//     public class MyMouseHandler extends MouseAdapter {
//         public void mousePressed(MouseEvent e) {
//             // System.out.println(e);
//             if (xRect.contains(e.getPoint())) {
//                JTabbedPane tb = (JTabbedPane)e.getSource();
//                int tabIndex = tabForCoordinate(tb, e.getX(), e.getY());
//                tb.remove(tabIndex);
//             }
//         }
//     }
// }
