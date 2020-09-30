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
		assertTrue("Fallo la validación de las condiciones", homePage.CantidadDeItemsCoincideConResultadoTotal()
				&& homePage.BreadCrumbContieneTexto("Heladeras con Frezzer") //En el documento se pide que se valide el string que figura en este método. Va a fallar esta validación ya que en la página el breadcrum es Frávega > Heladeras, Freezers y Cavas > Heladeras
				&& homePage.ItemsContienenSamsungEnElTitulo());
	}

}
