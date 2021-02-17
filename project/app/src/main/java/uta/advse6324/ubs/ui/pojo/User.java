package uta.advse6324.ubs.ui.pojo;


import java.io.Serializable;

public class User implements Serializable{


    // username password phone
    private String id;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String phone;
    private String email;

    public User(
            String id,
            String username,
            String password,
            String lastname,
            String firstname,
            String phone,
            String email
    ) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

//    @Override
//    public String toString() {
//        return "[User]" +
//                "\nusername=" + username +
//                "\npassword=" + password +
//                "\nrole=" + role +
//                "\nuta_id=" + uta_id +
//                "\nlastname=" + lastname +
//                "\nfirstname=" + firstname +
//                "\nphone=" + phone +
//                "\nemail=" + email +
//                "\naddress=" + address +
//                "\ncity=" + city +
//                "\nstate=" + state +
//                "\nzipcode=" + zipcode +
//                "\nmember=" + member +
//                "\nstatus="+status +
//                "\n";
//    }

}
