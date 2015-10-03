package pojo;

/**
 * Created by jeka on 24.09.2015.
 */
public class NameSheet {
    private String name;
    private String ip;
    private String login;
    private String password;

   public NameSheet(String Hostname) {
        this.name = Hostname;

   }

    public NameSheet(String Hostname, String Login, String Password, String IP) {
        this.name = Hostname;
        this.ip = IP;
        this.login = Login;
        this.password = Password;
    }

    public NameSheet() {
    }

    public String getName() {
        return name;
    }
    public String getIp() {return ip;}
    public String getLogin() {return login;}
    public String getPassword() {return password;}
}
