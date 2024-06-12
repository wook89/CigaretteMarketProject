package CigarMarket;

import java.io.IOException;

import CigarMarket.controller.CigarMarketController;
import CigarMarket.model.Cart;
import CigarMarket.model.CigarStorage;
import CigarMarket.view.ConsoleView;

public class CigarMarket {

	public static void main(String[] args)throws IOException {
		//model 
		CigarStorage cigarStorage = new CigarStorage();
		Cart cart = new Cart();
		//view
		ConsoleView view = new ConsoleView();
		//controller
		CigarMarketController controller = new CigarMarketController(cigarStorage,cart,view);
		controller.start();
	}

}
