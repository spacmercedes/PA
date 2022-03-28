public class MyException extends Exception{
    public MyException(String message){
        super("This command"+ message +"doesnt exist!");
    }
}
