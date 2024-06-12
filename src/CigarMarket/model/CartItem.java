package CigarMarket.model;

public class CartItem {
    
    Cigar cigar;
    int cigarId;
    int quantity;
    
    public CartItem(Cigar cigar) {
        this.cigar = cigar;
        this.cigarId = cigar.getCigarId();
        this.quantity = 1;
    }
    
    public Cigar getCigar() {
        return cigar;
    }

    public void setCigar(Cigar cigar) {
        this.cigar = cigar;
    }

    public int getCigarId() {
        return cigarId;
    }

    public void setCigarId(int cigarId) {
        this.cigarId = cigarId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return quantity * cigar.getPrice();
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
    
    @Override
    public String toString() {
        return cigar.getCigarId() + " <" + cigar.getName() + "> [" + quantity + "개] " + getPrice() + "원";
    }
}
