package br.com.microservice.dbservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DbServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*this.repository.deleteAll();

		// save a couple of customers
		this.repository.save(new Customer(UUIDs.timeBased(), "Alice", "Smith"));
		this.repository.save(new Customer(UUIDs.timeBased(), "Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : this.repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();*/

	}
}
