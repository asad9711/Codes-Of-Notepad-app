
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


import java.io.*;


import java.awt.event.*;

import java.util.*;

public class Menu implements ActionListener
{
	JFrame f;
	//JButton b;
	JTextArea ta;
	JMenuBar mb;
	JMenu file,search,settings;
	JMenuItem open,New,saveas;
	FileWriter fw;JFileChooser c;
	//JButton b;
	public Menu()
	{
		//super(x);
		Font mono = new Font("Monospaced", Font.BOLD, 16);
		f=new JFrame("menu");
		//b=new JButton("ok");
		mb=new JMenuBar();
		ta=new JTextArea();
		ta.setFont(mono);
		file=new JMenu("file");
		search=new JMenu("search");
	     settings=new JMenu("settings");
		 open=new JMenuItem("open");
		 New=new JMenuItem("new");
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
		f.add(ta);
		f.setJMenuBar(mb);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,800);
		f.setVisible(true);
  

	}
	public void actionPerformed(ActionEvent ae)
	{
		
		c=new JFileChooser();
		c.setDialogTitle("choose file");
	
		f.add(c);

	try{
      fw=new FileWriter("ab.txt");
      
        fw.write(ta.getText());
        fw.close();
	}
    catch(IOException e)
      {
      	System.out.println(e);
      }	
  }
	public static void main(String s[])
	{
		new Menu();
	}
	
}
//public class Choose
//{

//}