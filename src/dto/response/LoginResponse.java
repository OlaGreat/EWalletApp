package dto.response;

public class LoginResponse {
    String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResponse{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
