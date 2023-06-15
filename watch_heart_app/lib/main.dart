import 'package:flutter/material.dart';
import 'package:health/health.dart';
import 'package:socket_io_client/socket_io_client.dart' as IO;

void main() {
  runApp(HeartRateApp());
}

class HeartRateApp extends StatefulWidget {
  @override
  _HeartRateAppState createState() => _HeartRateAppState();
}

class _HeartRateAppState extends State<HeartRateApp> {
  
  String _heartRate = 'N/A';
  late IO.Socket socket;
  HealthFactory health = HealthFactory();

  @override
  void initState() {
    super.initState();
    _initializeSocket();
    _fetchHeartRate();
  }

  @override
  void dispose() {
    socket.disconnect();
    super.dispose();
  }

  Future<void> _initializeSocket() async {
    try {
      socket = IO.io('http://127.0.0.1:5000'); // Replace with your server URL
      socket.connect();
    } catch (e) {
      print('Error connecting to the server: $e');
    }
  }

  Future<void> _fetchHeartRate() async {
    // define the types to get
    var types = [
      HealthDataType.HEART_RATE
    ];

    var permissions = [
      HealthDataAccess.READ,
    ];

    // requesting access to the data types before reading them
    bool requested = await health.requestAuthorization(types, permissions: permissions);

    var heartRate;

    if (requested) {
      try {
        var healthDataPoints = await health.getHealthDataFromTypes(DateTime.now().subtract(const Duration(seconds: 3)), DateTime.now(), types);
        heartRate = healthDataPoints.first.value.toString();
      } catch (error) {
        print("Caught exception in getHealthDataFromTypes: $error");
      }

      print('Total number of steps: $_heartRate');

      setState(() {
        _heartRate = (heartRate == null) ? 0 : heartRate;
      });
    } else {
      print("Authorization not granted - error in authorization");
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Heart Rate App'),
        ),
        body: Center(
          child: Text(
            'Heart Rate: $_heartRate',
            style: TextStyle(fontSize: 24),
          ),
        ),
      ),
    );
  }
}
