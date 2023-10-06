from flask_socketio import send, emit
from flask import Flask, render_template
from flask_socketio import SocketIO

app = Flask(__name__)
app.config['SECRET_KEY'] = 'secret!'
socketio = SocketIO(app)

@app.route('/')
def index():
    return render_template('index.html', heart_rate=last_heart_rate)

last_heart_rate = None

@socketio.on('connect')
def handle_connect():
    print('Client connected')

@socketio.on('disconnect')
def handle_disconnect():
    print('Client disconnected')

@socketio.on('heart_rate')
def handle_heart_rate(heart_rate):
    global last_heart_rate
    last_heart_rate = heart_rate
    print('Heart rate logged:', last_heart_rate)
    socketio.emit('new_heart_rate', heart_rate+5)

if __name__ == '__main__':
    socketio.run(app)