from flask_socketio import send, emit
from flask import Flask, render_template
from flask_socketio import SocketIO
import datetime

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
    timestamp = datetime.datetime.now().strftime('%H:%M:%S')
    log_entry = f'{timestamp} - {last_heart_rate}'

    # Append log to the text file
    log_filename = 'heart_rate_log.txt'
    with open(log_filename, 'a+') as file:
        file.seek(0)
        first_char = file.read(1)
        
        # Check if the file is empty, if so, write the current date on the first line
        if not first_char:
            current_date = datetime.datetime.now().strftime('(%d:%m:%y)\n')
            file.write(current_date)

        file.write(log_entry + '\n')

    print('Heart rate logged:', last_heart_rate)
    socketio.emit('new_heart_rate', heart_rate)

if __name__ == '__main__':
    socketio.run(app)
