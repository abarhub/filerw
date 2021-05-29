package com.github.abarhub.filerw;

public class StubField implements Field {

    private final int position;
    private final int len;
    private final String name;

    public StubField(int position, int len, String name) {
        this.position = position;
        this.len = len;
        this.name = name;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public int getLength() {
        return len;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
