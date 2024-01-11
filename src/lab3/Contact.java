package lab3;

public class Contact {
    private String phoneNumber;
    private String name;
    private String email;
    private String extra_data;

    public Contact(String phoneNumber, String name, String email, String extra_data) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.extra_data = extra_data;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getExtra_data(){
        return extra_data;
    }

}

