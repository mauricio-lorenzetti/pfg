# Use a Python base image
FROM python:3.9

# Set the working directory inside the container
WORKDIR /app

# Copy the requirements.txt file to the container
COPY requirements.txt .

# Install the required dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Install Redis server
RUN apt-get update && apt-get install -y redis-server

# Expose the Redis default port
EXPOSE 6379

# Copy the application code to the container
COPY . .

# Set the environment variables
ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0

EXPOSE 5000

# Start the Flask application with Flask-SocketIO
CMD ["flask", "run", "--host", "0.0.0.0"]
