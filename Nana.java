import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Nana extends HttpServlet
{
	public void service(HttpServletRequest request
			, HttpServletResponse response)
			throws IOException, ServletException
	{
		OutputStream os = response.getOutputStream();
		PrintStream out = new PrintStream(os,true);
		out.println("Hello Servlet!!");
	}
}