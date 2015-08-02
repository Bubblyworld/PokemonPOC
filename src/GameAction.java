//Models an action that happens at a particular game tick.
//When the action update is called, the delta given is the number
// of ticks that have passed since the action started, or since last
// update otherwise. When an action is complete, it must
// call setComplete().
public abstract class GameAction implements Comparable<GameAction> {
    public long activationTick;
    public boolean isComplete;

    public GameAction(long activationTick) {
        this.activationTick = activationTick;
        this.isComplete = false;
    }

    public abstract void update(float currentTickF);

    public void setComplete() {
        isComplete = true;
    }

    public int compareTo(GameAction other) {
        if (activationTick < other.activationTick)
            return -1;
        if (activationTick == other.activationTick)
            return 0;
        return 1;
    }
}
