package com.retail.shop.retailapimain.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.retail.shop.retailapimain.model.Product;

@RestController
public class RetailController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/products/{id}")
	public String getProductList(@PathVariable("id") long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8090/product/" + id, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public Product createProducts(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
		return restTemplate.exchange("http://localhost:8090/product", HttpMethod.POST, entity, Product.class).getBody();
	}

	 @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	   public String deleteProduct(@PathVariable("id") long id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8090/product/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }
}
