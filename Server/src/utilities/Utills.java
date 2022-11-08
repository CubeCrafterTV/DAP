package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utills {
	public static int port;
	public static int ip;
	public static String getTime() {
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
	}
}
