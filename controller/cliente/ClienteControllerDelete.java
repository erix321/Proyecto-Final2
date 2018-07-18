package controller.cliente;
import java.io.IOException;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.UserServiceFactory;
import controller.*;
import model.*;
public class ClienteControllerDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(AccesoUsuario.dameAcceso(request.getServletPath(),UserServiceFactory.getUserService().getCurrentUser(), 
				PMF.get().getPersistenceManager(), getServletContext(),request, response)){
			Jaula borrar = pm.getObjectById(Jaula.class, Long.parseLong(request.getParameter("accountID")));
			try {
				pm.deletePersistent(borrar);
			} finally {
				pm.close();
			}
			response.sendRedirect("/Clientes");
		}
	}
}
