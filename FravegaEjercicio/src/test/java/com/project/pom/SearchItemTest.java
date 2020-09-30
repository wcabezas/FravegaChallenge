package com.project.pom;

import static org.junit.Assert.*;
import org.junit.Test;

public class SearchItemTest extends BaseTest {
	HomePage homePage = new HomePage();

	@Test
	public void testBuscarHeladerasSamsung() throws Exception {
		homePage.BuscarItem("Heladeras");
		homePage.AplicarFiltroHeladera();
		homePage.AplicarFiltroSamsung();
		assertTrue("Fallo la validaci�n de las condiciones", homePage.CantidadDeItemsCoincideConResultadoTotal()
				&& homePage.BreadCrumbContieneTexto("Heladeras con Frezzer") //En el documento se pide que se valide el string que figura en este m�todo. Va a fallar esta validaci�n ya que en la p�gina el breadcrum es Fr�vega > Heladeras, Freezers y Cavas > Heladeras
				&& homePage.ItemsContienenSamsungEnElTitulo());
	}

}
