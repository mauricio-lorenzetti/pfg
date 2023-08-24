import socketio

# Create a SocketIO client instance
sio = socketio.Client()

@sio.on('connect')
def on_connect():
    print('Connected to server')

@sio.on('disconnect')
def on_disconnect():
    print('Disconnected from server')

@sio.on('new_heart_rate')
def on_new_heart_rate(data):
    print('Received new heart rate:', data)

# Establish a connection to the SocketIO server
sio.connect('http://localhost:5000')

# Emit the "heart_rate" event with a heart rate value
heart_rate_value = 75  # Replace with the actual heart rate value
sio.emit('heart_rate', heart_rate_value)

# Wait for a moment to allow the event to be sent
sio.sleep(2)

# Disconnect from the server
sio.disconnect()
