package highlight;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import fit.fit;

public class SyntaxHighlighter implements DocumentListener {

	public static  Set<String> keywords;
	private Set<String> Extrewords;
	
	public static int a = 1;

	private Style keywordStyle;

	private Style normalStyle;

	private Style ExtrewordStyle;

	public SyntaxHighlighter(JTextPane editor) throws IOException{

		// 准备着色使用的样式

		keywordStyle = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);

		normalStyle = ((StyledDocument) editor.getDocument()).addStyle("Keyword_Style", null);

		ExtrewordStyle = ((StyledDocument) editor.getDocument()).addStyle("Extra_Sytle", null);
		
		StyleConstants.setForeground(keywordStyle, Color.RED);

		StyleConstants.setForeground(normalStyle, Color.BLACK);

		StyleConstants.setForeground(ExtrewordStyle, Color.GREEN);

		// 准备关键字

		keywords = new HashSet<String>();
		
		FileReader fd = new FileReader("F://敏感词.txt");
		BufferedReader bReader = new BufferedReader(fd);
		String s = null;
		while((s = bReader.readLine()) != null) {
			keywords.add(s);
			//System.out.println(s);
		}
		fd.close();

		//keywords.add("html");

		//keywords.add("文件操作");
		//keywords.add("解析");

		//keywords.add("private");

		//keywords.add("_int9");

		//keywords.add("float");

		//keywords.add("double");

		//keywords.add("<");
		
		Extrewords = new HashSet<String>();
		Extrewords.add("title");
		Extrewords.add("字符串");
		Extrewords.add("代码如下");
	}



	public void colouring(StyledDocument doc, int pos, int len) throws BadLocationException {

		// 取得插入或者删除后影响到的单词.

		// 例如"public"在b后插入一个空格, 就变成了:"pub lic", 这时就有两个单词要处理:"pub"和"lic"

		// 这时要取得的范围是pub中p前面的位置和lic中c后面的位置

		int start = indexOfWordStart(doc, pos);

		int end = indexOfWordEnd(doc, pos + len);



		char ch;

		while (start < end) {

			ch = getCharAt(doc, start);

			if (Character.isLetter(ch) || ch == '_') {

				// 如果是以字母或者下划线开头, 说明是单词

				// pos为处理后的最后一个下标

				start = colouringWord(doc, start);

			} else {

				SwingUtilities.invokeLater(new ColouringTask(doc, start, 1, normalStyle));

				++start;

			}

		}

	}



	/**

	 * 对单词进行着色, 并返回单词结束的下标.

	 * 

	 * @param doc

	 * @param pos

	 * @return

	 * @throws BadLocationException

	 */

	public int colouringWord(StyledDocument doc, int pos) throws BadLocationException {

		
		int wordEnd = indexOfWordEnd(doc, pos);

		String word = doc.getText(pos, wordEnd - pos);
		
		

		if (keywords.contains(word)) {
			
			SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, keywordStyle));	
		
		}else if(Extrewords.contains(word)){
			
			SwingUtilities.invokeLater(new ColouringTask(doc, pos, wordEnd - pos, ExtrewordStyle));
		
		}else {
			int index = 0;
			Iterator<String> it = keywords.iterator(); 
			while(it.hasNext()) {
				String sensitiveword  = it.next();
				index = word.indexOf(sensitiveword);
				while (index != -1) {
					SwingUtilities.invokeLater(new ColouringTask(doc, pos + index, wordEnd - pos, ExtrewordStyle));
					index = word.indexOf(sensitiveword, index + 1);//*从这个索引往后开始第一个出现的位置
				}
			}
		}
		return wordEnd;

	}

public void sss(StyledDocument doc, int pos, String str) throws BadLocationException{
	int index = 0;
	int i = 1;
	Iterator<String> it = keywords.iterator(); 
	
	while(it.hasNext()) {
		
		
		System.out.println("第"+i+"个词");
		i++;
		String sensitiveword  = it.next();
		
		//String str = doc.getText();
		
		//System.out.println(str);
		
		index = str.indexOf(sensitiveword);
		
		System.out.println(sensitiveword);
		
		while (index != -1) {
			System.out.println("出现位置为"+index+"长度"+sensitiveword.length());
			String s2 = str.substring(index, (index + sensitiveword.length()));
			if(s2.equals(sensitiveword)) {
			SwingUtilities.invokeLater(new ColouringTask(doc, index, sensitiveword.length(), keywordStyle));
			}
			index = str.indexOf(sensitiveword,index+1);//*从这个索引往后开始第一个出现的位置
		}
	}
}

	/**

	 * 取得在文档中下标在pos处的字符.

	 * 

	 * 如果pos为doc.getLength(), 返回的是一个文档的结束符, 不会抛出异常. 如果pos<0, 则会抛出异常.

	 * 所以pos的有效值是[0, doc.getLength()]

	 * 

	 * @param doc

	 * @param pos

	 * @return

	 * @throws BadLocationException

	 */

	public char getCharAt(Document doc, int pos) throws BadLocationException {

		return doc.getText(pos, 1).charAt(0);

	}



	/**

	 * 取得下标为pos时, 它所在的单词开始的下标. Â±wor^dÂ± (^表示pos, Â±表示开始或结束的下标)

	 * 

	 * @param doc

	 * @param pos

	 * @return

	 * @throws BadLocationException

	 */

	public int indexOfWordStart(Document doc, int pos) throws BadLocationException {

		// 从pos开始向前找到第一个非单词字符.

		for (; pos > 0 && isWordCharacter(doc, pos - 1); --pos);



		return pos;

	}



	/**

	 * 取得下标为pos时, 它所在的单词结束的下标. Â±wor^dÂ± (^表示pos, Â±表示开始或结束的下标)

	 * 

	 * @param doc

	 * @param pos

	 * @return

	 * @throws BadLocationException

	 */

	public int indexOfWordEnd(Document doc, int pos) throws BadLocationException {

		// 从pos开始向前找到第一个非单词字符.

		for (; isWordCharacter(doc, pos); ++pos);



		return pos;

	}



	/**

	 * 如果一个字符是字母, 数字, 下划线, 则返回true.

	 * 

	 * @param doc

	 * @param pos

	 * @return

	 * @throws BadLocationException

	 */

	public boolean isWordCharacter(Document doc, int pos) throws BadLocationException {

		char ch = getCharAt(doc, pos);

		if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_') { return true; }

		return false;

	}



	@Override

	public void changedUpdate(DocumentEvent e) {



	}



	@Override

	public void insertUpdate(DocumentEvent e) {

		try {
			if(a == 1) {
				//在这实现
			//colouring((StyledDocument) e.getDocument(), e.getOffset(), e.getLength());
			//System.out.println(e.getDocument().getText(e.getOffset(), e.getLength()));
			sss((StyledDocument) e.getDocument(), 1, e.getDocument().getText(e.getOffset(), e.getLength()));
			//System.out.println("111");
			a = 0;
			}
		} catch (BadLocationException e1) {

			e1.printStackTrace();

		}

	}



	@Override

	public void removeUpdate(DocumentEvent e) {

		try {
			
			
			// 因为删除后光标紧接着影响的单词两边, 所以长度就不需要了
			sss((StyledDocument) e.getDocument(), 1, e.getDocument().getText(e.getOffset(), e.getLength()));
			//colouring((StyledDocument) e.getDocument(), e.getOffset(), 0);

		} catch (Exception e1) {

			//e1.printStackTrace();

		}

	}



	/**

	 * 完成着色任务

	 * 

	 * @author Biao

	 * 

	 */

	private class ColouringTask implements Runnable {

		private StyledDocument doc;

		private Style style;

		private int pos;

		private int len;



		public ColouringTask(StyledDocument doc, int pos, int len, Style style) {

			this.doc = doc;

			this.pos = pos;

			this.len = len;

			this.style = style;

		}



		public void run() {

			try {

				// 这里就是对字符进行着色

				doc.setCharacterAttributes(pos, len, style, true);

			} catch (Exception e) {}

		}

	}

}
