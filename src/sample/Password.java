package sample;

public class Password {
    private int password_id;
    private int user_id;

    private String salt;
    private String hash;
    private String pinhash;

    public String getSalt(){
        return salt;
    }
    public boolean comaparePin(String inPinHash){
        return inPinHash.equals(pinhash);
    }
    public boolean compareHash(String inHssh){
        return inHssh.equals(hash);
    }
}