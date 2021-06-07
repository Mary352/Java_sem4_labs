package by.belstu.kryukova.CallCenterTask;

public class CallCenter {
    //private int amountOfOperators;
    private Client[] operators;

    public CallCenter(int size) {
        this.operators = new Client[size];
    }

    public void showOperatorsState() {
        System.out.println("---------------------------------");
        System.out.println("Cостояние операторов: ");
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] != null) {
                System.out.println(i + ") " + "занят с клиентом #" + operators[i].getClientName());
            } else {
                System.out.println(i + ") свободен");
            }
        }
        System.out.println("---------------------------------");
    }

    public void connect(Client client) {

        operators[this.getFreeOperator()] = client;
    }

    public void disconnect(Client client) {
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == client) {
                operators[i] = null;
            }
        }
    }

    public int getFreeOperator() {
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
