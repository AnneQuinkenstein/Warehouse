public class Command {
    public enum Operator{ CREATE, READ, UPDATE, DELETE, ERROR }
    public final Operator operator;

    public final String[] input;
    public Command(String text){
       input = text.split(" ");

        switch (input[0]){
            case ":c" ->  this.operator=Operator.CREATE;
            case ":r" ->  this.operator=Operator.READ;
            case ":u" ->  this.operator=Operator.UPDATE;
            case ":d" ->  this.operator=Operator.DELETE;
            default ->  this.operator=Operator.ERROR;

        }
    }

    public String[] readInput(){
        return input;
    }
}
