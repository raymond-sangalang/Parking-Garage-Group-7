package personel;

public abstract class User {
	
    protected String username;
    protected String password;


    public User(String username, String password) { 
        this.username = username;
        this.password = password;

    }

    public boolean checkCredentials(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }


    public String getUsername() {
        return username;
    }
}
