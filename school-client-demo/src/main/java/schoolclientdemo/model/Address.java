package schoolclientdemo.model;


public class Address {
	
	private String province;
	private String district;
	private String neighborhood;
	private String street;
	private String buildingNo;
	private String apartmentNo;
	
	public Address() {}

	public Address(String province, String district, String neighborhood, String street, String buildingNo,
			String apartmentNo) {
		this.province = province;
		this.district = district;
		this.neighborhood = neighborhood;
		this.street = street;
		this.buildingNo = buildingNo;
		this.apartmentNo = apartmentNo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public String getApartmentNo() {
		return apartmentNo;
	}

	public void setApartmentNo(String apartmentNo) {
		this.apartmentNo = apartmentNo;
	}

}
