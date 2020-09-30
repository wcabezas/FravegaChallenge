package com.project.api;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;


import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiTest {

	@Test
	public void cerveceriaObtenidaCoincideConDatos() throws JsonParseException, JsonMappingException, IOException {
		RestAssured.baseURI = "https://api.openbrewerydb.org/breweries/autocomplete";
		RequestSpecification httpRequest = RestAssured.given().param("query", "lagunitas");
		Response response = httpRequest.request(Method.GET);
		
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();	
		
		//Mapeamos el response obtenido en una lista de Cerveceria
		ObjectMapper objectMapper = new ObjectMapper();
		List<Cerveceria> listCerveceria = objectMapper.readValue(responseBody, new TypeReference<List<Cerveceria>>() {}); 

		//Aplicamos un filtro en las cervecerias obtenidas en el primer paso
		List<Cerveceria> cerveceriasFiltradasPorNombre = Cerveceria.FiltrarCerveceriaPorNombre("Lagunitas Brewing Co", listCerveceria);
		Cerveceria cerveceria = Cerveceria.ObtenerCerveceriaPorState(cerveceriasFiltradasPorNombre,"California");
		
		//Verificamos que los datos de la cerveceria resultante con "State:California" coincida con los datos mencionados en el documento
		System.out.println("Status Code => " + statusCode);
		System.out.println("ResponseBody is => " + responseBody);
		assertTrue("Los datos de la cerveceria no coinciden",cerveceria.VerificacionDeDatosDeCerveceria(
				"761","Lagunitas Brewing Co","1280 N McDowell Blvd","7077694495"));

	}

}
