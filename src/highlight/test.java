package highlight;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.SwingUtilities;



public class test {
	
	public static void main(String[] args) {
		Set<String> s = new HashSet<String>();
		s.add("public");

		s.add("protected");

		s.add("private");

		s.add("_int9");

		s.add("float");

		s.add("double");

		s.add("<");
		
		String str = "I am a boy; public apublicas";
		
		int index = 0;
		Iterator<String> it = s.iterator(); 
		while(it.hasNext()) {
			System.out.println("222");
			String sensitiveword  = it.next();
			index = str.indexOf(sensitiveword);
			System.out.println(index);
			System.out.println(sensitiveword);
			while (index != -1) {
				System.out.println("333");
				index = str.indexOf(sensitiveword,index+1);//*从这个索引往后开始第一个出现的位置
				System.out.println(index);
	}
}
		}
}
