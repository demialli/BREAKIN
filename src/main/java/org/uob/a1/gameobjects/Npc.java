// this one is pretty much done!!
// lol not true
// go define the npc in map!

package org.uob.a1.gameobjects;

public class Npc {

    private String name;
    private String[] dialogue;
    
    public Npc(String name, String[] dialogue) {
        this.name = name;
        this.dialogue = dialogue;
    }

    // getters
    public String getName() {
        return name;
    }

    public String[] getDia() {
        return dialogue;
    }

    // todo: setters
}
