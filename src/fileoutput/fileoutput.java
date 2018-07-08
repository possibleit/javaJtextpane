package fileoutput;
import java.io.FileOutputStream;
import java.io.IOException;
public class fileoutput {
	public static void fileout(String str) throws IOException {
		FileOutputStream fos = new FileOutputStream("F://ÎÄ±¾Êä³ö.txt");
		fos.write(str.getBytes());
		fos.close();
	}
	
		public static void fileout2(String str, String str2)throws IOException {
			FileOutputStream fos = new FileOutputStream(str2,true);
			fos.write(str.getBytes());
			fos.close();
}
		public static void fileout3(String str, String str2)throws IOException {
			FileOutputStream fos = new FileOutputStream(str2);
			fos.write(str.getBytes());
			fos.close();
}
}
