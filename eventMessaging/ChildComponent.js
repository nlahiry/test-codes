import { dispatchNotification } from './EventSystem';

const ChildComponent = () => {
  const handleClick = () => {
    const notificationData = { message: 'This is a notification!', type: 'info' };
    dispatchNotification(notificationData);
  };

  return (
    <button onClick={handleClick}>Show Notification</button>
  );
};

export default ChildComponent;
