public class SessionObjectEvent {

    private final MySessionObject sessionObject;

    public SessionObjectEvent(MySessionObject sessionObject) {
        this.sessionObject = sessionObject;
    }

    public MySessionObject getSessionObject() {
        return sessionObject;
    }
}
