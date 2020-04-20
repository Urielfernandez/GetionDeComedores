package codigoPrincipal;
import datos.*;
import java.util.*;
public interface IGestionDatos {
	
	public ArrayList<Menu> leerMenus();
	public void almacenarCompra(Bandeja bandeja);
	public void almacenarMenu(Menu menu);
	public void almacenarValoración(Bandeja bandeja);
}
