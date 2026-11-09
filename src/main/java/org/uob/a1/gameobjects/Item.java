package org.uob.a1.gameobjects;

public class Item {

    private String name;
    private String description;
    private Room usedIn;
    private String usedText;
    private String type;
    private Object target;    
    
    public Item(String name, String description, Room usedIn, String usedText, String type, Object target) {
        this.name = name;
        this.description = description;
        this.usedIn = usedIn;
        this.usedText = usedText;
        this.type = type;
        this.target = target;
    }
}
