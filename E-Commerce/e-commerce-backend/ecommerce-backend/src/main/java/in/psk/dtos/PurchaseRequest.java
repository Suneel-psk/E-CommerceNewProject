package in.psk.dtos;

import java.util.Set;

import in.psk.entitys.Address;
import in.psk.entitys.Customer;
import in.psk.entitys.Order;
import in.psk.entitys.OrderItem;

public class PurchaseRequest {
	
	private Customer customer;
	private Address shippingAddress;
	private Order order;
	private Set<OrderItem> orderItems;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	

}
