package CigarMarket.controller;

import CigarMarket.model.Admin;
import CigarMarket.model.Cart;
import CigarMarket.model.CigarStorage;
import CigarMarket.model.Customer;
import CigarMarket.view.ConsoleView;

public class CigarMarketController {

    ConsoleView view;
    CigarStorage mCigarStorage;
    Cart mCart;
    Customer mCustomer;
    Admin mAdmin;
    
    String[] menuList = {
        "0.종료",
        "1.상품 정보 보기",
        "2.장바구니 보기",
        "3.장바구니에 상품 추가",
        "4.장바구니에서 상품 삭제",
        "5.장바구니 상품 수량 변경",
        "6.장바구니 비우기",
        "7.주문하기",
        "8.관리자 모드"
    };
    String[] adminMenuList = {
        "0.종료",
        "1.신규 상품 등록",
        "2.상품 정보 삭제",
        "3.상품 정보 보기",
        "4.상품 정보 저장"
    };
    
    public CigarMarketController(CigarStorage cigarStorage, Cart cart, ConsoleView view) {
        this.view = view;
        mCigarStorage = cigarStorage;
        mCart = cart;
        mAdmin = new Admin();
    }

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
            case 4 -> deleteCigarInCart();
            case 5 -> updateCigarInCart();
            case 6 -> resetCart();
            case 7 -> order();
            case 8 -> adminMode();
            case 0 -> end();
            }
            
        } while(menu != 0);
    }
    
    private void adminMode() {
        
        if(!authenticateAdmin()) {
            view.showMessage("관리자 인증 실패.");
            return;
        }
        
        int menu; 
        do {
            menu = view.selectMenu(adminMenuList);
            switch(menu) {
            case 1 -> addCigar2Storage();
            case 2 -> deleteCigarInStorage();
            case 3 -> viewCigarInfo();
            case 4 -> saveCigarList2File();
            case 0 -> adminEnd();
            default -> view.showMessage("잘못된 메뉴 번호입니다.");
            }
            
            
        } while(menu != 0);
    }
    
    // 장바구니 상품 수량 변경
    private void updateCigarInCart() {
        // 장바구니 출력
        view.displayCart(mCart);
        if(!mCart.isEmpty()) {
            // 상품 ID 입력받기
            int cigarId = view.selectCigarId(mCigarStorage);
            // 수량 입력받기
            int quantity = view.inputQuantity(0, mCigarStorage.getMaxQuantity());
            // 상품 ID에 해당하는 cartItem 찾아서 cartItem quantity 수정 set.
            mCart.updateQuantity(cigarId, quantity);
        }
        
    }

    // 장바구니 상품 삭제
    private void deleteCigarInCart() {
        // 장바구니 출력
        view.displayCart(mCart);
        if(!mCart.isEmpty()) {
            // 상품ID 입력받기
            int cigarId = view.selectCigarId(mCigarStorage);
            if(view.askConfirm("해당 상품을 삭제하시겠습니까? yes를 입력하세요.", "yes")) {
                // 해당 ID에 해당하는 cartItem을 삭제
                mCart.deleteItem(cigarId);
                view.showMessage(">> 해당 상품을 삭제하였습니다.");
            }
        }
    }

    
    private void adminEnd() {
        if(!mCigarStorage.isSaved()) {
            view.showMessage("상품 정보가 저장되지 않았습니다.");
            if(view.askConfirm("상품 정보를 저장하시겠습니까? yes를 입력하세요 : ", "yes")) {
                mCigarStorage.saveCigarList2File();
                System.out.println("상품 정보가 저장되었습니다.");
            }
        }
        view.showMessage("관리자 모드를 종료합니다.\n");
        System.exit(0);
    }

    private void saveCigarList2File() {
        if(mCigarStorage.isSaved()) {
            view.showMessage("상품 정보가 이미 저장되어 있습니다.");
        } else {
            mCigarStorage.saveCigarList2File();
            view.showMessage("상품 정보가 저장되었습니다.");
        }
    }

    private void deleteCigarInStorage() {
        if(mCigarStorage.isEmpty()) {
            view.showMessage("저장된 상품이 없습니다.");
            return;
        }
        // 저장된 상품 출력
        viewCigarInfo();
        // 상품 ID 입력 받기
        int cigarId = view.selectCigarId(mCigarStorage);
        if(view.askConfirm(">> 해당 상품을 삭제하시겠습니까? yes를 입력하세요.", "yes")) {
            // 해당 상품ID의 cartItem 삭제
            mCigarStorage.deleteItem(cigarId);
            view.showMessage(">> 해당 상품을 삭제하였습니다.");
        }
    }

    private void addCigar2Storage() {
        view.showMessage("신규 상품을 추가합니다.");
        
        // 상품정보를 Cigar 객체로 생성하여 mCigarStorage에 추가
        mCigarStorage.addCigar(view.inputString("상품 이름 : "),
                               view.inputString("타르 함량 : "),
                               view.inputString("니코틴 함량 : "),
                               view.readNumber("상품 가격 : "));
        //String name, double tar, double nicotine, int price
    }
    

    private boolean authenticateAdmin() { // 관리자 id,pw 확인
        view.showMessage("관리자 인증을 진행해주세요.");
        String id = view.inputString("관리자 ID :");
        String pw = view.inputString("관리자 PW :");

        return mAdmin.login(id, pw);
    }

    private void end() {
        view.showMessage(">> CigarMaket 프로그램 종료.");
    }

    private void order() {
        // 배송정보 추가
        getDeliveryInfo();
        
        // 주문정보 확인 (장바구니, 배송정보)
        viewOrderInfo();
        
        // 최종 주문할지 확인
        if(view.askConfirm("최종 주문하시겠습니까? yes를 입력하세요 : ","yes")) {
        
        // 주문확정 -> 장바구니 초기화
        mCart.resetCart();
        }
    }

    private void viewOrderInfo() {
        view.displayOrder(mCart, mCustomer);
    }

    private void getDeliveryInfo() {
        view.inputDeliveryInfo(mCustomer);
    }

    // 장바구니 비우기
    private void resetCart() {
        view.displayCart(mCart);
        
        if(!mCart.isEmpty()) {
            if(view.askConfirm(">> 장바구니를 비우시겠습니까? yes를 입력하세요.", "yes")) {
            mCart.resetCart();
            view.showMessage(">> 장바구니를 비웠습니다.");
            }
        }
    }

    private void addCigar2Cart() {
        view.displayCigarInfo(mCigarStorage);
        int cigarId = view.selectCigarId(mCigarStorage);
        mCart.addItem(mCigarStorage.getCigarById(cigarId));
        view.showMessage(">> 장바구니에 상품을 추가했습니다.");
    }

    private void viewCart() {
        view.displayCart(mCart);
    }

    private void viewCigarInfo() {
        view.displayCigarInfo(mCigarStorage);
    }

    private void resisterCustomerInfo() {
        mCustomer = new Customer();
        view.inputCustomerInfo(mCustomer);
    }

    private void welcome() {
        view.displayWelcome();
    }
    
}
