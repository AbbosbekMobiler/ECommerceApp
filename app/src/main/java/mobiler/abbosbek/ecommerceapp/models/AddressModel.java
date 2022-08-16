package mobiler.abbosbek.ecommerceapp.models;

import mobiler.abbosbek.ecommerceapp.activities.AddressActivity;

public class AddressModel {
    String userName;
    String userAddress;
    boolean isSelected;

    public AddressModel(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
