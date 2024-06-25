const Notification = ({ notification }) => {
  if (!notification) {
    return null;
  }

  const { message, type } = notification;

  // ... (same styling logic as previous example)

  return (
    <div className={`fixed top-10 right-10 z-50 ${notificationStyle}`}>
      {message}
    </div>
  );
};

export default Notification;
