import 'package:flutter/material.dart';
import 'package:pedometer/pedometer.dart';
import 'package:wear/wear.dart';
import 'dart:async';
import 'package:permission_handler/permission_handler.dart';

//HEART RATE
import 'package:flutter/services.dart';
import 'package:heart_rate_flutter/heart_rate_flutter.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: podometerScreen(),
    );
  }
}

class podometerScreen extends StatefulWidget {
  const podometerScreen({Key? key}) : super(key: key);

  @override
  State<podometerScreen> createState() => _podometerScreenState();
}

class _podometerScreenState extends State<podometerScreen> {
  @override
  void initState() {
    super.initState();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.black,
        child: Center(
          child: WatchShape(
              builder: (BuildContext context, WearShape shape, Widget? child) {
                return AmbientMode(
                  builder: (BuildContext context, WearMode mode, Widget? child) {
                    return mode == WearMode.active
                        ? const MainScreen()
                        : const MainScreen();
                  },
                );
              }),
        ),
      ),
    );
  }
}

class MainScreen extends StatefulWidget {
  const MainScreen({Key? key}) : super(key: key);

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  late Stream<StepCount> _stepCountStream;
  late Stream<PedestrianStatus> _pedestrianStatusStream;
  String _status = '?', _steps = '?';

  //HEART RATE
  final HeartRateFlutter _heartRateFlutterPlugin = HeartRateFlutter();
  var heartBeatValue = 0;

  var stepsCounter = 0;
  var stepStatus = '';

  @override
  void initState() {
    //HEART RATE
    _heartRateFlutterPlugin.init();
    _listener();

    super.initState();
    initPlatformState();
    setState(() {});
  }

  void onStepCount(StepCount event) {
    /// Handle step count changed
    setState(() {
      _steps = event.steps.toString();
    });
  }

  void onPedestrianStatusChanged(PedestrianStatus event) {
    /// Handle status changed
    print(event.status);
    setState(() {
      _status = event.status;
    });
  }

  void onPedestrianStatusError(error) {
    print("Error: $error");
    setState(() {
      _status = "NO";
    });
    print(_status);
  }

  void onStepCountError(error) {
    print("Error: $error");
    setState(() {
      _steps = "Conteo de pasos no disponible";
    });
    print(_steps);
  }

  void initPlatformState() {
    checkPermissions();
    _pedestrianStatusStream = Pedometer.pedestrianStatusStream;
    _pedestrianStatusStream
        .listen(onPedestrianStatusChanged)
        .onError(onPedestrianStatusError);

    _stepCountStream = Pedometer.stepCountStream;
    _stepCountStream.listen(onStepCount).onError(onStepCountError);

    if (!mounted) return;
  }

  void checkPermissions() async {
    var status = await Permission.activityRecognition.status;
    //HEART RATE
    var statusHeartRate = await Permission.sensors.status;

    Map<Permission, PermissionStatus> statuses =
    await [Permission.activityRecognition, Permission.sensors].request();
    if (statuses[Permission.activityRecognition.isDenied] != null && statuses[Permission.sensors.isDenied] != null) {
      print("sensor access is Denied");

    } else {
      print("Permission Granted");
    }


    /*Map<Permission, PermissionStatus> statusesHeartRate =
        await [Permission.sensors].request();
    if (statusesHeartRate[Permission.sensors.isDenied] != null) {
      print("sensor access HEART RATE is Denied");
    } else {
      print("Permission HEART RATE Granted");
    }*/
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Divider(
              height: 40,
              thickness: 0,
              color: Colors.black,
            ),
            Text(
              'Ritmo cardiaco: $heartBeatValue',
              style: TextStyle(fontSize: 15, color: Colors.white),
            ),
            Divider(
              height: 15,
              thickness: 0,
              color: Colors.black,
            ),
            Text(
              _steps == "Conteo de pasos no disponible" ||
                  _steps != "Conteo de pasos no disponible"
                  ? 'Contador de pasos: $stepsCounter'
                  : 'Contador de pasos: $_steps',
              style: TextStyle(fontSize: 15, color: Colors.white),
            ),
            Divider(
              height: 15,
              thickness: 0,
              color: Colors.black,
            ),
            Text(
              'Estado:',
              style: TextStyle(fontSize: 15, color: Colors.white),
            ),
            Icon(
              _status == 'walking' || stepStatus == "Caminando"
                  ? Icons.directions_walk
                  : _status == 'stopped' || stepStatus == "Detenido"
                  ? Icons.accessibility_new
                  : Icons.error,
              size: 50,
              color: _status == 'walking' || stepStatus == "Caminando"
                  ? Colors.green
                  : _status == 'stopped' || stepStatus == "Detenido"
                  ? Colors.amber
                  : Colors.red,
            ),
            Center(
                child: Text(
                  _status == '?' || _status != '?'
                      ? stepStatus
                      : _status,
                  style: _status == 'walking' || stepStatus == "Caminando"
                      ? TextStyle(fontSize: 15, color: Colors.green)
                      : _status == 'stopped' || stepStatus == "Detenido"
                      ? TextStyle(fontSize: 15, color: Colors.amber)
                      : TextStyle(fontSize: 15, color: Colors.red),
                )
            )
          ],
        ),
      ),
    );
  }

  //HEART RATE
  void _listener() {
    _heartRateFlutterPlugin.heartBeatStream.listen((double event) {
      if (mounted) {
        setState(() {
          heartBeatValue = event.toInt();
          if (_steps == "Conteo de pasos no disponible") {
            stepsCounter++;
            if (heartBeatValue > 0) {
              stepStatus = "Caminando";
            }
            else {
              stepStatus = "Detenido";
            }
          }
        });
      }
    });
  }
}

class Ambient extends StatefulWidget {
  const Ambient({Key? key}) : super(key: key);

  @override
  State<Ambient> createState() => _AmbientState();
}

class _AmbientState extends State<Ambient> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(
              'FlutterOS',
              style: TextStyle(color: Colors.blue[600], fontSize: 30),
            ),
            SizedBox(height: 15),
            const FlutterLogo(size: 60.0),
          ],
        ),
      ),
    );
  }
}