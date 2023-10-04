import java.util.Vector;

public class ModelVector extends Vector<ClientModel> {
    @Override
    public boolean contains(Object o) {
        if (o instanceof ClientModel client) {
            for (ClientModel clientModel : this) {
                if (clientModel.getAddress().equals(client.getAddress()) && clientModel.getPort() == client.getPort()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        if (o instanceof ClientModel client) {
            for (int i = 0; i < size(); i++) {
                if (this.get(i).getAddress().equals(client.getAddress()) && this.get(i).getPort() == client.getPort()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
