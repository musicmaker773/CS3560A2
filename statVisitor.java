public class statVisitor implements Visitor {

    public String visitor(numOfUsers u) {
        return " # of Users: ";
    }
    public String visitor(numOfGroups g) {
        return " # of Groups: ";
    }
    public String visitor(numOfMessages m) {
        return " # of Messages: ";
    }
    public String visitor(positiveMessages pm) {
        return " % of Positive Message: ";
    }
}
