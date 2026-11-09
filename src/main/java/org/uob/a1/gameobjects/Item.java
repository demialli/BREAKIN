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

    // getters
    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }

    public Room getRoomUsedIn() {
        return usedIn;
    }

    public String getUsedText() {
        return usedText;
    }

    public String getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public void setRoomUseIn(Room usedIn) {
        this.usedIn = usedIn;
    }

    public void setUsedText(String usedText) {
        this.usedText = usedText;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
