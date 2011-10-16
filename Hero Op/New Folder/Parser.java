import java.net.*;
import java.io.*;

public class Parser {
    public static void main(String[] args) throws Exception {
		String stringToReverse = "wilan";

		URL url = new URL("http://www.elveron.com/elveron/index.jsp");
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);

		OutputStreamWriter out = new OutputStreamWriter(
								  connection.getOutputStream());
		out.write("uname=" + stringToReverse);
		out.close();

		BufferedReader in = new BufferedReader(
					new InputStreamReader(
					connection.getInputStream()));
					
		String decodedString;

		while ((decodedString = in.readLine()) != null) {
			System.out.println(decodedString);
		}
		in.close();
	}
}
