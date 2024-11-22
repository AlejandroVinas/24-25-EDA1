package entregas.Vinas_Alejandro.Reto_005;

class Node {
    String id;
    String[] data; 
    Node parent; 
    List children; 

    // Constructor
    public Node(String id, String[] data, Node parent) {
        this.id = id;
        this.data = data;
        this.parent = parent;
        this.children = new List();
    }

    
    public Node addChild(String id, String[] additionalData) {
        
        String[] childData = new String[this.data.length + additionalData.length];
        System.arraycopy(this.data, 0, childData, 0, this.data.length);
        System.arraycopy(additionalData, 0, childData, this.data.length, additionalData.length);

        Node childNode = new Node(id, childData, this);
        this.children.add(childNode); 
        return childNode;
    }
}