package Core;

import java.util.Stack;

public class MyStringBuilder {
    private StringBuilder stringBuilder;
    private Stack<MySnapshot> snapshots;

    public MyStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        snapshots = new Stack<>();
    }
    public void save() {
        snapshots.push(new MySnapshot(stringBuilder.toString()));
    }
    public MyStringBuilder undo() {
        if(!snapshots.empty()) {
            stringBuilder = new StringBuilder(snapshots.pop().sb()); }
        return this;
    }
    public MyStringBuilder reverse(){
        MySnapshot shot = new MySnapshot(stringBuilder.toString());
        snapshots.push(shot);
        stringBuilder.reverse();
        return this;
    }
    public MyStringBuilder append(int i) {
        MySnapshot shot = new MySnapshot(stringBuilder.toString());
        snapshots.push(shot);
        stringBuilder.append(i);
        return this;
    }

    @Override
    public String toString() {
        return "MyStringBuilder{" +
                "stringBuilder=" + stringBuilder +
                '}';
    }
}
