package explore.oop.incorporate;

public class Customer {
    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", customerId=" + customerId +
                ", customerPin=" + customerPin +
                '}';
    }

    public Customer(String customerName, Integer customerId, Integer customerPin) {
        this.customerName = customerName;
        this.customerId = customerId;
        this.customerPin = customerPin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getCustomerPin() {
        return customerPin;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerPin(Integer customerPin) {
        this.customerPin = customerPin;
    }

    private  String customerName;
    private Integer customerId;
    private Integer customerPin;


}
