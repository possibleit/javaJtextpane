package mainFrame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import GUIUtil.GUIUtil;
import first.*;
import fileoutput.fileoutput;
import gethtml.gethtml;
import getstatic.getstatic;
import highlight.SyntaxHighlighter;

public class mainFrame extends JFrame implements ActionListener{
	
	
	public String string;
	public String string1;
	
	public BorderLayout bLayout = new BorderLayout(10,10);
	
	private JLabel jlb = new JLabel("输入网址",4);
	private JButton jbt1 = new JButton("HTML");
	private JButton jbt2 = new JButton("提取文本");
	private JButton jbt3 = new JButton("一键提取文本");
	private JTextField jtf1 = new JTextField(20);
	private JTextPane txt = new JTextPane();
	
	private JScrollPane jsp = new JScrollPane(txt);
	private JPanel jpl1 = new JPanel();
	private JPanel jpl2 = new JPanel();
	private JPanel jpl3 = new JPanel();

	
	
	public mainFrame() throws IOException {
		super("java");
		
		txt.getDocument().addDocumentListener(new SyntaxHighlighter(txt));
		this.getContentPane().add(jsp);
		
		jpl1.setLayout(bLayout);
		//txt.setWrapStyleWord(true);
		
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		jpl3.add(jlb);
		jpl3.add(jtf1);
		jpl2.add(jbt1);
		jpl2.add(jbt2);
		jpl2.add(jbt3);
		jpl1.add(jpl3,BorderLayout.NORTH);
		jpl1.add(jsp,BorderLayout.CENTER);
		jpl1.add(jpl2,BorderLayout.SOUTH);
		
		GUIUtil.tocenter(this);
		this.add(jpl1);
		
		
		
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		/*************************增加监听********************************/
		jbt1.addActionListener((ActionListener) this);
		jbt2.addActionListener((ActionListener) this);
		jbt3.addActionListener((ActionListener) this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbt1) {
			try {
					string = jtf1.getText();
					string1 = gethtml.gethtml(string);
					txt.setText(string1);
					SyntaxHighlighter.a = 1;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}else if(e.getSource() == jbt2) {
			try {
				string = jtf1.getText();
				string1 = JavacuiSpider.getText(string);
				txt.setText(string1);
				fileoutput.fileout(string1);
				SyntaxHighlighter.a = 1;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == jbt3) {
			try {
			String string3 = txt.getText();
			getstatic.fileout(string3);
			}catch (IOException ex) {
				// TODO: handle exception
			}
		}else {
			JOptionPane.showMessageDialog(this, "error");
		}
	}
	public static void main(String[] args) throws IOException {
		new mainFrame();
	}
}
