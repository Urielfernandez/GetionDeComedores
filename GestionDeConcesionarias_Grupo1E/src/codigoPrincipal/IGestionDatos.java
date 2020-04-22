package codigoPrincipal;
import datos.*;
import java.util.*;
import org.json.*;
public interface IGestionDatos {
	
	public ArrayList<Menu> leerMenus();
	public void almacenarCompra(Bandeja bandeja);
	public void almacenarMenu(Menu menu);
	public void almacenarValoracion(Bandeja bandeja);
	
}
