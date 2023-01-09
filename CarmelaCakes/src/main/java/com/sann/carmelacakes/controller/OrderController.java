package com.sann.carmelacakes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sann.carmelacakes.model.CustomerOrder;
import com.sann.carmelacakes.repository.CakeRepository;
import com.sann.carmelacakes.repository.CustomerOrderRepository;
import com.sann.carmelacakes.repository.CustomerRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private Environment env; // loads properties from application.properties

	@Autowired
	CustomerOrderRepository customerOrderRepository;
	@Autowired
	CakeRepository cakeRepository;
	@Autowired
	CustomerRepository customerRepository;

	private final Logger log = LoggerFactory.getLogger(OrderController.class);

	@GetMapping("/all")
	public Iterable<CustomerOrder> allOrders() {
		return customerOrderRepository.findAll();
	}

	@PostMapping("/new")
	public ResponseEntity<CustomerOrder> newOrder(@RequestBody CustomerOrder newOrderRequest) {

		scheduleDelivery(newOrderRequest);
		try {
			cakeRepository.save(newOrderRequest.getCake());
			customerRepository.save(newOrderRequest.getCustomer());
			CustomerOrder newOrder = customerOrderRepository.save(newOrderRequest);
			return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error saving order: {}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Send order details to WeDeliver's schedule delivery API
	 */
	private void scheduleDelivery(CustomerOrder newOrderRequest) {
		try {
			String deliveryJson = prepareDeliveryJson(newOrderRequest);

			String newWeDeliverApiUrl = env.getProperty("new.wedeliver.api.url");
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");

			okhttp3.RequestBody body = okhttp3.RequestBody.create(deliveryJson, mediaType);
			Request request = new Request.Builder().url(newWeDeliverApiUrl).post(body)
					.addHeader("Content-Type", "application/json").build();

			client.newCall(request).execute();
		} catch (Exception e) {
			log.error("Error connecting to WeDeliver: {}", e.getMessage());
		}
	}

	private String prepareDeliveryJson(CustomerOrder newOrderRequest) {
		String json = "{\r\n" + "        \"id\": 2,\r\n" + "        \"creationDate\": \"2023-01-06\",\r\n"
				+ "        \"scheduledDate\": \"2023-01-12\",\r\n" + "        \"scheduledTime\": \"14:00:00\",\r\n"
				+ "        \"address\": \"unit 12, 106 Tokyo Street\",\r\n" + "        \"rider\": {\r\n"
				+ "            \"id\": 2,\r\n" + "            \"name\": \"Brandon Bottles\",\r\n"
				+ "            \"vehicleType\": \"Car\",\r\n" + "            \"isAvailable\": true\r\n"
				+ "        },\r\n" + "        \"customer\": \"CarmelaCakes\"\r\n" + "    }";
		return json;
	}
}
