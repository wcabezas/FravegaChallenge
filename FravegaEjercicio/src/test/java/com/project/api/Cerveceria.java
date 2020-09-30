package com.project.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cerveceria {
	 private String id;
	 private String name;

	 public String id() {
		 return id;
	 }
	 public String name() {
		 return name;
	 }
	 
	 public void setName(String name) {
		 this.name = name;
	 }
	 
	 public void setId(String id) {
		 this.id = id;
	 }

	 //Filtra las cervecerias por nombre
	 public static List<Cerveceria> FiltrarCerveceriaPorNombre(String nombre, List<Cerveceria> lista){
		 List<Cerveceria> lista1 = new ArrayList<Cerveceria>();
		 for (Cerveceria c: lista) {
			 if(c.name().equals(nombre)) {
				 lista1.add(c);
			 }
		 } 
		 return lista1;
	 }
	 
	 //Obtiene una cervecería dependiendo del State que se le pase por parámetro
	 public static Cerveceria ObtenerCerveceriaPorState(List<Cerveceria> lista, String state) throws IOException {
		 ObjectMapper objectMapper = new ObjectMapper();
		 for (Cerveceria c: lista) {
			 RestAssured.baseURI = "https://api.openbrewerydb.org/breweries/"+c.id();
			 Response response = RestAssured.given().request(Method.GET);
			 JsonNode jsonNodeRoot = objectMapper.readTree(response.getBody().asString());
			 JsonNode jsonNodeState = jsonNodeRoot.get("state");
			 if(jsonNodeState.asText().equals(state)) {
				 return c;
			 }
		 }
		 return null;
	 }
	 
	 //Valida que los datos ingresados por parámetro coincidan con los del ojeto json obtenido 
	 public Boolean VerificacionDeDatosDeCerveceria(String id, String name, String street, String phone) throws IOException {
	 	ObjectMapper objectMapper = new ObjectMapper();
		RestAssured.baseURI = "https://api.openbrewerydb.org/breweries/"+this.id;
		Response response = RestAssured.given().request(Method.GET);
		JsonNode jsonNodeRoot = objectMapper.readTree(response.getBody().asString());
		JsonNode jsonNodeId = jsonNodeRoot.get("id");
		JsonNode jsonNodeName = jsonNodeRoot.get("name");
		JsonNode jsonNodeStreet = jsonNodeRoot.get("street");
		JsonNode jsonNodePhone = jsonNodeRoot.get("phone");
		 
		return jsonNodeId.asText().equals(id)
			&&	jsonNodeName.asText().equals(name)
			&& 	jsonNodeStreet.asText().equals(street)
			&& 	jsonNodePhone.asText().contentEquals(phone);
	 }
}
