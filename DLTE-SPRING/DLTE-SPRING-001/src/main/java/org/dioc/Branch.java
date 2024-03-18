package org.dioc;

public class Branch {
    private String branchName;
    private String ifsCode;
    private String branchId;
    private String bankName;
    private Long branchContact;

    public Branch() {
    }

    public Branch(String branchName, String ifsCode, String branchId, String bankName, Long branchContact) {
        this.branchName = branchName;
        this.ifsCode = ifsCode;
        this.branchId = branchId;
        this.bankName = bankName;
        this.branchContact = branchContact;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branchName='" + branchName + '\'' +
                ", ifsCode='" + ifsCode + '\'' +
                ", branchId='" + branchId + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branchContact=" + branchContact +
                '}';
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfsCode() {
        return ifsCode;
    }

    public void setIfsCode(String ifsCode) {
        this.ifsCode = ifsCode;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getBranchContact() {
        return branchContact;
    }

    public void setBranchContact(Long branchContact) {
        this.branchContact = branchContact;
    }
}