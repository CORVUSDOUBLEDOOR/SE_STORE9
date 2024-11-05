public class Member {
    String idUser;
    String firstnameUser;
    String lastnameUser;
    String emailUser;
    String passwordUser;
    String phoneUser;
    String pointUser;

    public Member(String idUser, String firstnameUser, String lastnameUser, String emailUser, String passwordUser, String phoneUser, String pointUser) {
        this.idUser = idUser;
        this.firstnameUser = firstnameUser;
        this.lastnameUser = lastnameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.phoneUser = phoneUser;
        this.pointUser = pointUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFirstnameUser() {
        return firstnameUser;
    }

    public void setFirstnameUser(String firstnameUser) {
        this.firstnameUser = firstnameUser;
    }

    public String getLastnameUser() {
        return lastnameUser;
    }

    public void setLastnameUser(String lastnameUser) {
        this.lastnameUser = lastnameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getPointUser() {
        return pointUser;
    }

    public void setPointUser(String pointUser) {
        this.pointUser = pointUser;
    }
}
