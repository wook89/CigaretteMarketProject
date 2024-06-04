package CigarMarket.controller;

import CigarMarket.model.Admin;
import CigarMarket.model.Cart;
import CigarMarket.model.CigarStorage;
import CigarMarket.model.Customer;
import CigarMarket.view.ConsoleView;

public class CigarMargetController {

	ConsoleView view;
	CigarStorage mCigarStorage;
	Cart mCart;
	Customer mCustomer;
	Admin mAdmin;
	
	String[] menuList = {
			"0.종료",
		"1.상품 보기",
		"2.장바구니 보기",
		"3.장바구니에 상품 추가",
		"4.장바구니 비우기",
		"5.상품 구매",
		"6.관리자 로그인"
	};
	
	public void start() {
		welcome();
		resisterCustomerInfo();
		int menu;
		do {
			menu = view.selectMenu(menuList);
			
			switch(menu) {
			case 1 -> viewCigarInfo();
			case 2 -> viewCart();
			case 3 -> addCigar2Cart();
			case 4 -> resetCart();
			case 5 -> order();
			case 6 -> adminMode();
			case 0 -> end();
			}
			
		}while(menu != 0);
	}

	private Object end() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object adminMode() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object order() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object resetCart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object addCigar2Cart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewCart() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object viewCigarInfo() {
		view.displayCigarInfo(mCigarStorage);
		return null;
	}

	private void resisterCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
	}

	private void welcome() {
		view.displayWelcome();
	}
	
}
