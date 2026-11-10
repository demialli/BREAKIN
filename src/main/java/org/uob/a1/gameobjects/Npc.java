package org.uob.a1.gameobjects;

public class Npc {

    private String name;
    private String dialogue;
    
    public Npc(String name, String dialogue) {
        this.name = name;
        this.dialogue = dialogue;
    }

    // getters
    public String getName() {
        return name;
    }

    public String getDia() {
        return dialogue;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDia(String dialogue) {
        this.dialogue = dialogue;
    }
}
