package org.webconsole.Details;

public class EmployeeAddress extends implementation.EmployeeAddress {
    private String address;
    private String houseNumber;
    private String state;
    private String city;
    private Integer pinCode;

    public EmployeeAddress() {
    }

    @Override
    public String toString() {
        return "EmployeeAddressConsole{" +
                "address='" + address + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    public EmployeeAddress(String address, String houseNumber, String state, String city, Integer pinCode) {
        this.address = address;
        this.houseNumber = houseNumber;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
    }
}
