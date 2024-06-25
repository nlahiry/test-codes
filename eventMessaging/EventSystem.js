import { useState, useEffect } from 'react';

const events = {};

const EventSystem = () => {
  const [notification, setNotification] = useState(null);

  useEffect(() => {
    const handleNotificationEvent = (data) => {
      setNotification(data);
    };

    // Add event listener for "notification" event
    window.addEventListener('notification', handleNotificationEvent);

    return () => {
      // Remove event listener on cleanup
      window.removeEventListener('notification', handleNotificationEvent);
    };
  }, []);

  // Function to dispatch notification event
  const dispatchNotification = (data) => {
    window.dispatchEvent(new CustomEvent('notification', { detail: data }));
  };

  return (
    <>
      {/* Your application components here */}
      {notification && <Notification notification={notification} />}
    </>
  );
};

export { EventSystem, dispatchNotification };
