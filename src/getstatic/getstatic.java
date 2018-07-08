package getstatic;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import fileoutput.fileoutput;
import first.JavacuiSpider;
import highlight.SyntaxHighlighter;
import highlight.SyntaxHighlighter;

public class getstatic {
	public static String string;
		public static void fileout(String str) throws IOException {
			//FileOutputStream staticout = new FileOutputStream("F://文本输出.txt");
			//staticout.write(str.getBytes());
			System.out.println(str);
			//先打开文件读取网址，对每个网址提取文本，输出
			int i = 1;
			FileReader fd = new FileReader(str);
			BufferedReader bReader = new BufferedReader(fd);
			String s = null;
			while((s = bReader.readLine()) != null) {
				int j = 1;
				string = JavacuiSpider.getText(s);
				//System.out.println(string);
				String a = String.valueOf(i);
				//System.out.println(string);
				String path = "F://"+ a +".txt";
				System.out.println(path);
				fileoutput.fileout3(string,path);
				i++;
				
				int index = 0;
				
				Iterator<String> it = SyntaxHighlighter.keywords.iterator(); 
				int m = 1;
				while(it.hasNext()) {
					String pathx = "F://" + a + "敏感词.txt";
					if(j == 1) {
						fileoutput.fileout3("\r\n",pathx);
					}
					j = 0;
					System.out.println("第"+m+"个词");
					m++;
					String sensitiveword  = it.next();
					
					//String str = doc.getText();
					
					//System.out.println(str);
					
					index = string.indexOf(sensitiveword);
					
					System.out.println(sensitiveword);
					
					while (index != -1) {
						String out1 = "敏感词" + sensitiveword;
						//fileoutput.fileout3("\r\n",pathx);
						fileoutput.fileout2(out1,pathx);          //不覆盖
						//System.out.println("出现位置为"+index+"长度"+sensitiveword.length());
						//String s2 = str.substring(index, (index + sensitiveword.length()));
						 
							String out2 = "出现位置" + String.valueOf(index) + "长度" + String.valueOf(sensitiveword.length())+"\r\n";
							System.out.println(out2);
							fileoutput.fileout2(out2,pathx);             //不覆盖
						
						index = str.indexOf(sensitiveword,index+1);//*从这个索引往后开始第一个出现的位置
					}
				}
			}
			bReader.close();
		}
		
		/*public static void main(String[] args) throws IOException {
			fileout("F://java.txt");
		}*/
}