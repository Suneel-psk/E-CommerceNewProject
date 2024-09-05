package in.psk.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.psk.dtos.PurchaseRequest;
import in.psk.dtos.PurchaseResponse;
import in.psk.service.OrderService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@PostMapping("/purchase")
	public PurchaseResponse createOrder(@RequestBody PurchaseRequest purchaseRequest) throws Exception {
		return orderService.placeOrder(purchaseRequest);
	}

}
