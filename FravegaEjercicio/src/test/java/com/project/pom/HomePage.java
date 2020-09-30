package com.project.pom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {

//////Declaramos los selectores de la página de resultados//////
	
	By searchInput = By.xpath("//input[@class='InputBar__SearchInput-t6v2m1-1 iJaFAt']");
	By searchButton = By.xpath("//button[@class='InputBar__SearchButton-t6v2m1-2 jRChuZ']");	
	By filtroHeladeras = By.xpath("//h4[contains(text(),'Heladeras (')]");
	By botonVerTodas = By.xpath("//a[@name='viewAllBrands']");
	By itemsHeladerasSamsung = By.xpath(".//div/*//li//div[@class='ProductCard__Card-sc-1w5guu7-2 hlRWOw']");
	By tituloItemHeladera = By.className("PieceTitle-sc-1eg7yvt-0 akEoc");
	By totalResultados = By.xpath(".//div//*/li[@name='totalResult']/span");
	By breadCrumb = By.xpath("//ul[@class='breadcrumbstyled__List-vxt6er-2 dFbIgZ']");
	By filtroSamsung = By.xpath("//label[@for='filterItemsamsung']");
	By botonApply = By.xpath("//button[@id='apply']");

	
	//Busca en la página el item ingresado por parámetro
	public void BuscarItem(String item) throws Exception {		
		try {
			FindElement(searchInput).click();
			FindElement(searchInput).clear();
			typeText(item, searchInput);
			click(searchButton);
		}
		catch(Exception e) {
			throw new Exception("Falló búsqueda de " + item);
		}
		
	}
	
	//////Este método hace un click en el filtro 'Heladeras'//////
	public void AplicarFiltroHeladera() throws Exception{
		
		try {
			click(filtroHeladeras);
		} catch (Exception e) {
			throw new Exception("Fallo filtro Heladera");
		}
	}
	
	//////Este método abre el modal de filtros de marcas y selecciona el filtro Samsung
	public void AplicarFiltroSamsung() throws Exception{
		try {
			click(botonVerTodas);
			click(filtroSamsung);
			click(botonApply);
		} catch (Exception e) {
			throw new Exception("Fallo filtro Samsung");
		}
	}

	//////Verifica que cada uno de los items contengan la palabra Samsung en el título
	public Boolean ItemsContienenSamsungEnElTitulo() throws Exception {
		int contador = 0;
		List<WebElement> itemsHeladera = findElements(itemsHeladerasSamsung);
		try {
			for (WebElement item: itemsHeladera) {
				if (getText(item).contains("Samsung")) {
					contador++;
				};
			}
			return itemsHeladera.size() == contador;
			
		}catch (Exception e) {
			throw new Exception("Fallo validacion de titulo");
		}
	}
	
	//////Verifica que la cantidad de items del resultado coincida con el contador de resultados
	public Boolean CantidadDeItemsCoincideConResultadoTotal() throws Exception{
		List<WebElement> itemsHeladera = findElements(itemsHeladerasSamsung);
		try {
			int cantidadDeItems = itemsHeladera.size();
			return getText(FindElement(totalResultados)).equals(((Integer)cantidadDeItems).toString());
			
		}catch (Exception e) {
			throw new Exception("Fallo validar la coincidencia de resultados");
		}
	}
	
	//////Verifica que el breadcrumb de la página contenga el texto ingresado por parámetro
	public Boolean BreadCrumbContieneTexto(String text) throws Exception {
		try {
			return getText(FindElement(breadCrumb)).contains(text);
			
		}catch (Exception e) {	
			throw new Exception("Fallo validar breadcrumb");		
		}
	}
}
