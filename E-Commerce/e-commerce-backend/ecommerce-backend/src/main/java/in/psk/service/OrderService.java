package in.psk.service;

import in.psk.dtos.PurchaseRequest;
import in.psk.dtos.PurchaseResponse;

public interface OrderService {

	public PurchaseResponse placeOrder(PurchaseRequest purchaseRequest)throws Exception;
}
