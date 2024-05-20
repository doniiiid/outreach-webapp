package com.outreach.rest;

import com.outreach.rest.model.Store;
import com.outreach.rest.model.StoreInventory;
import com.outreach.rest.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ServiceApplication {

	@Autowired
	private StoreRepository storeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		String[] category = new String[1];
		category[0] = "Convience";
		Store store = new Store(1l, "CVS", category);
		List<StoreInventory> storeInventoryList = new ArrayList<>();

		storeInventoryList.add(new StoreInventory(
				2l,
				"Soap",
				2.99f,
				10,
				store
		));
		store.setStoreInventoryList(storeInventoryList);


		storeRepository.save(store);
	}
}
