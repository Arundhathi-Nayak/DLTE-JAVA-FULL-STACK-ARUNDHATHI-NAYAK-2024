package store.oops;

public class BondMain {
    private String bondName;
    private Integer maturity;

    public BondMain(String bondName, Integer maturity, Double interestRate, String taxStatus, Integer period, String bondHolder) {
        this.bondName = bondName;
        this.maturity = maturity;
        this.interestRate = interestRate;
        this.taxStatus = taxStatus;
        this.period = period;
        this.bondHolder = bondHolder;
    }

    private  Double interestRate;
    private String taxStatus;
    private Integer period;
    private String bondHolder;

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public Integer getMaturity() {
        return maturity;
    }

    public void setMaturity(Integer maturity) {
        this.maturity = maturity;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getBondHolder() {
        return bondHolder;
    }

    public void setBondHolder(String bondHolder) {
        this.bondHolder = bondHolder;
    }



}
