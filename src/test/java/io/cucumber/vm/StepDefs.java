package io.cucumber.vm;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs {
	private Product product = new Product();
	private List<InventoryItem> inventory = new ArrayList<>();
	private VendingMachine vm;

	public void fillInventory() {
		inventory.add(new InventoryItem(new Product("chips"), 10));
		inventory.add(new InventoryItem(new Product("chocolate"), 10));
		inventory.add(new InventoryItem(new Product("candy"), 10));
		inventory.add(new InventoryItem(new Product("cookie"), 10));
		inventory.add(new InventoryItem(new Product("water"), 10));
		inventory.add(new InventoryItem(new Product("juice"), 10));
		inventory.add(new InventoryItem(new Product("coke"), 10));
		inventory.add(new InventoryItem(new Product("pepsi"), 10));
	}

	@Given("user want to buy {string}")
	public void user_want_to_buy(String name) {
		fillInventory();
		vm = new VendingMachine(inventory);
		product.setName(name);
		vm.setProduct(product);
	}

	@When("user insert the {double} dollars")
	public void user_insert_the_15dolars1(Double amount) {
		vm.setAmount(amount);
		vm.checkForChange();
	}

	@When("press the button with the code")
	public void press_the_button_with_the_code1() {

	}

	@Then("The {string} leaves the machine and the stock reduces in 1unit")
	public void the_chips_leaves_the_machine_and_the_stock_reduces_in_1unit(String product) {
		vm.removeInventory(product);
		assertEquals(product, vm.getProduct().getName());
		assertEquals(9, vm.getInventoryQtyForThe(product));
	}

	@And("VM give {double} back")
	public void vm_give_back(Double change) {
		// assertEquals(vm.getChange(), change);
		// assertEquals(change, vm.getChange());

	}

	@Then("The VM ask for {string} {double} dollars")
	public void the_VM_ask_for_00dollars(String message, Double missing) {
		assertEquals(message + missing, vm.getAmountMissingMessage());
	}

}
