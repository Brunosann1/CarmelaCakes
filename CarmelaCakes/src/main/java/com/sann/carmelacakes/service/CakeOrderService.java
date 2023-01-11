package com.sann.carmelacakes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sann.carmelacakes.controller.CakeOrderController;
import com.sann.carmelacakes.model.CakeOrder;
import com.sann.carmelacakes.repository.CakeOrderRepository;
import com.sann.carmelacakes.repository.CakeRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Service
public class CakeOrderService {

	@Autowired
	private Environment env; // loads properties from application.properties

	@Autowired
	private CakeOrderRepository cakeOrderRepository;

	@Autowired
	private CakeRepository cakeService;

	@Autowired
	private CustomerService customerService;

	private final Logger log = LoggerFactory.getLogger(CakeOrderController.class);

	public CakeOrder newCakeOrder(CakeOrder cakeOrder) {

		setDeliverySchedule(cakeOrder);
		return saveNewCakeOrder(cakeOrder);
	}

	private CakeOrder saveNewCakeOrder(CakeOrder cakeOrder) {
		try {
			cakeOrder.setCake(cakeService.save(cakeOrder.getCake()));
			cakeOrder.setCustomer(customerService.save(cakeOrder.getCustomer()));
			
			return save(cakeOrder);
		} catch (Exception e) {
			log.error("Error saving cake order: {}", e.getStackTrace().toString());
			return null;
		}
	}

	private CakeOrder save(CakeOrder cakeOrder) {
		return cakeOrderRepository.save(cakeOrder);
	}

	/*
	 * Consumes WeDeliver's API and tries to schedule the cakeOrder delivery
	 */
	private void setDeliverySchedule(CakeOrder cakeOrder) {

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

	private String prepareDeliveryJson(CakeOrder newOrderRequest) {
		String json = "{\r\n" + "        \"id\": 2,\r\n" + "        \"creationDate\": \"2023-01-06\",\r\n"
				+ "        \"scheduledDate\": \"2023-01-12\",\r\n" + "        \"scheduledTime\": \"14:00:00\",\r\n"
				+ "        \"address\": \"unit 12, 106 Tokyo Street\",\r\n" + "        \"rider\": {\r\n"
				+ "            \"id\": 2,\r\n" + "            \"name\": \"Brandon Bottles\",\r\n"
				+ "            \"vehicleType\": \"Car\",\r\n" + "            \"isAvailable\": true\r\n"
				+ "        },\r\n" + "        \"customer\": \"CarmelaCakes\"\r\n" + "    }";
		return json;
	}

	public Iterable<CakeOrder> findAll() {
		return cakeOrderRepository.findAll();
	}
}
