
// program and analytic comments written by https://github.com/maroulakion

// HOW TO CALCULATE DISTANCE of sensor to objects
// -> hardware needed:  Arduino microcontroller (eg. Arduino UNO R3)
//                      Breadboard
//                      Jumper wires
//                      Ultrasonic Sensor

// standard idea imported by https://create.arduino.cc/projecthub/abdularbi17/ultrasonic-sensor-hc-sr04-with-arduino-tutorial-327ff6

#define trig 9 // 3rd pin of sensor, about emitting sound waves
#define echo 8 // 2nd pin of sensor, about receiving sound waves

long duration; // variable for the duration of sound wave travel
int distance; // variable for the distance measurement

void setup() {
  pinMode(trig, OUTPUT);    // Trigger speaker needs to emit waves to "hit" the closest objects, 
                            // so we declare is as an OUTPUT
  pinMode(echo, INPUT);     // Echo speaker need to receive object's reaction to trigger's waves,
                            // so we declare it as an INPUT
                            
  Serial.begin(9600);       // Start communication with Serial monitor (screen on pc)
}


void loop() {
  // Empty buffer of Trigger speaker
  digitalWrite(trig, LOW);      // turn trigger speaker off
  delayMicroseconds(2);         // let speak stay off for a bit
  // Start emitting waves from trigger speaker
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);        // keep emitting for 10 microSeconds
  digitalWrite(trig, LOW);
  
  // Catch objects' reaction to the previous emitted sound waves with Echo pin
  // Save how long it took to waves to returns in the Echo speaker
  duration = pulseIn(echo, HIGH);
  
  // Calculating the distance using the speed of sound wave 
  // and divided it by 2, because
  // sound waves travel and "hit" and object after X cm, stop there,
  // and produce an equal sound wave with reserve direction 
  // travelling another X cm back to Echo pin of the ultra sonic sensor
  distance = duration * 0.034 / 2; 
    
  // Show distance on the serial terminal 
  Serial.print("Distance (in cm): ");
  Serial.print(distance);
}
