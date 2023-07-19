package dto.response;

public class RegisteredWalletResponse {
    private String userName;
    private String message;
    private String accountNumber;
    private int id;



    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegisteredWalletResponse{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", accountNumber='").append(accountNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
