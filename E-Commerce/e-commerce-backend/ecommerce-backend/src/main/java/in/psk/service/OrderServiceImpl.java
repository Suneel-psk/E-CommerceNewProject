package in.psk.service;

import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import in.psk.dtos.PurchaseRequest;
import in.psk.dtos.PurchaseResponse;
import in.psk.entitys.Customer;
import in.psk.entitys.Order;
import in.psk.entitys.OrderItem;
import in.psk.repository.CustomerRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Value("${razorpay.key.id}")
	private String keyId="";
	@Value("${razorpay.key.secret}")
	private String keySecret="";
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public PurchaseResponse placeOrder(PurchaseRequest purchaseRequest) throws Exception {
		Order order = purchaseRequest.getOrder();
		//Setting Order Tracking Number
		String orderTrackingNumber = UUID.randomUUID().toString();
		order.setOrderTrackingNumber(orderTrackingNumber);
		order.setStatus("pending");
		//Populate OrderItems In Order
		Set<OrderItem> orderItems = purchaseRequest.getOrderItems();
		orderItems.forEach(item->order.add(item));
		//Populate Shipping Address
		order.setShippingAddress(purchaseRequest.getShippingAddress());
		//Populate Customer with Order
		Customer customer = purchaseRequest.getCustomer();
		String email = customer.getEmail();
		  // check customer presence in db
		Customer customerFromDb = customerRepository.findByEmail(email);

		if (customerFromDb == null) {
			  // we found customer
            customer = customerFromDb;
		}

		 // adding order to customer and vice versa
        customer.add(order);

        // save data in db
        customerRepository.save(customer);

		String razorpayOrderId = createRazorpayOrder(purchaseRequest);

		return new PurchaseResponse(orderTrackingNumber, razorpayOrderId);

	}

	private String createRazorpayOrder(PurchaseRequest purchaseRequest) throws RazorpayException {
		JSONObject orderRequest=new JSONObject();
		orderRequest.put("amount", purchaseRequest.getOrder().getTotalPrice()*100);
		orderRequest.put("currency","INR");
		//orderRequest.put("reciept",purchaseRequest.getCustomer().getEmail());
		RazorpayClient client=new RazorpayClient(keyId,keySecret);
		com.razorpay.Order razorPayOrder = client.Orders.create(orderRequest);
		return razorPayOrder.get("id");
	}

}
