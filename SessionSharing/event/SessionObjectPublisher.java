@Service
public class MyService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private SessionDetails sessionDetails;

    public void createSessionObject() {
        // ... initialize session object
        eventPublisher.publishEvent(sessionDetails.getSessionData());
    }
}
