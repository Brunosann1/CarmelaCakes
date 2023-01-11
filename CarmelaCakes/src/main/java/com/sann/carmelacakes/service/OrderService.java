package com.sann.carmelacakes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sann.carmelacakes.controller.OrderController;
import com.sann.carmelacakes.model.Order;
import com.sann.carmelacakes.repository.OrderRepository;
import com.sann.carmelacakes.repository.CakeRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
public class OrderService {

	@Autowired
	private Environment env; // loads properties from application.properties

	@Autowired
	private OrderRepository cakeOrderRepository;

	@Autowired
	private CakeRepository cakeService;

	@Autowired
	private CustomerService customerService;

	private final Logger log = LoggerFactory.getLogger(OrderController.class);

	public Order newCakeOrder(Order cakeOrder) {

		setDeliverySchedule(cakeOrder);
		return saveNewCakeOrder(cakeOrder);
	}

	private Order saveNewCakeOrder(Order cakeOrder) {
		try {
			cakeOrder.setCake(cakeService.save(cakeOrder.getCake()));
			cakeOrder.setCustomer(customerService.save(cakeOrder.getCustomer()));
			
			return save(cakeOrder);
		} catch (Exception e) {
			log.error("Error saving cake order: {}", e.getStackTrace().toString());
			return null;
		}
	}

	private Order save(Order cakeOrder) {
		return cakeOrderRepository.save(cakeOrder);
	}

	/*
	 * Consumes WeDeliver's API and tries to schedule the cakeOrder delivery
	 */
	private void setDeliverySchedule(Order cakeOrder) {

		try {
			String deliveryJson = prepareDeliveryJson(cakeOrder);

			String newWeDeliverApiUrl = env.getProperty("newOrder.wedeliver.api.url");
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");

			RequestBody body = RequestBody.create(deliveryJson, mediaType);
			Request request = new Request.Builder().url(newWeDeliverApiUrl).post(body)
					.addHeader("Content-Type", "application/json").build();
			client.newCall(request).execute();

		} catch (Exception e) {
			log.error("Error connecting to WeDeliver: {}", e.getStackTrace().toString());
		}

	}

	private String prepareDeliveryJson(Order newOrderRequest) {
		String json = "{\r\n" + "        \"id\": 2,\r\n" + "        \"creationDate\": \"2023-01-06\",\r\n"
				+ "        \"scheduledDate\": \"2023-01-12\",\r\n" + "        \"scheduledTime\": \"14:00:00\",\r\n"
				+ "        \"address\": \"unit 12, 106 Tokyo Street\",\r\n" + "        \"rider\": {\r\n"
				+ "            \"id\": 2,\r\n" + "            \"name\": \"Brandon Bottles\",\r\n"
				+ "            \"vehicleType\": \"Car\",\r\n" + "            \"isAvailable\": true\r\n"
				+ "        },\r\n" + "        \"customer\": \"CarmelaCakes\"\r\n" + "    }";
		return json;
	}

	public Iterable<Order> findAll() {
		return cakeOrderRepository.findAll();
	}
}
