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
			//FileOutputStream staticout = new FileOutputStream("F://�ı����.txt");
			//staticout.write(str.getBytes());
			System.out.println(str);
			//�ȴ��ļ���ȡ��ַ����ÿ����ַ��ȡ�ı������
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
					String pathx = "F://" + a + "���д�.txt";
					if(j == 1) {
						fileoutput.fileout3("\r\n",pathx);
					}
					j = 0;
					System.out.println("��"+m+"����");
					m++;
					String sensitiveword  = it.next();
					
					//String str = doc.getText();
					
					//System.out.println(str);
					
					index = string.indexOf(sensitiveword);
					
					System.out.println(sensitiveword);
					
					while (index != -1) {
						String out1 = "���д�" + sensitiveword;
						//fileoutput.fileout3("\r\n",pathx);
						fileoutput.fileout2(out1,pathx);          //������
						//System.out.println("����λ��Ϊ"+index+"����"+sensitiveword.length());
						//String s2 = str.substring(index, (index + sensitiveword.length()));
						 
							String out2 = "����λ��" + String.valueOf(index) + "����" + String.valueOf(sensitiveword.length())+"\r\n";
							System.out.println(out2);
							fileoutput.fileout2(out2,pathx);             //������
						
						index = str.indexOf(sensitiveword,index+1);//*�������������ʼ��һ�����ֵ�λ��
					}
				}
			}
			bReader.close();
		}
		
		/*public static void main(String[] args) throws IOException {
			fileout("F://java.txt");
		}*/
}