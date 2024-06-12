package CigarMarket.model;

public class Cigar {
	private int cigarId;
	private String name;
	private String tar;
	private String nicotine;
	private int price;
	
	public Cigar(int cigarId, String name, String tar, String nicotine, int price) {
		this.cigarId = cigarId;
		this.name = name;
		this.tar = tar;
		this.nicotine = nicotine;
		this.price = price;	
	}
	
	public int getCigarId() {
		return cigarId;
	}

	public void setCigarId(int cigarId) {
		this.cigarId = cigarId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTar() {
		return tar;
	}

	public void setTar(String tar) {
		this.tar = tar;
	}

	public String getNicotine() {
		return nicotine;
	}

	public void setNicotine(String nicotine) {
		this.nicotine = nicotine;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return cigarId+"  <"+name+"> "+tar+", "+nicotine+"  ["+price+"원]";
	}
	
}
