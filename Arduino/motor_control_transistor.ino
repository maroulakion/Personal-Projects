
// program and comments written by https://github.com/maroulakion

// inspired from 
// https://docs.arduino.cc/learn/electronics/transistor-motor-control

// SPIN DC MOTOR to max value when a button is pressed
// Control speed of motor using a transistor
// Hardware needed: Arduino microcontroller (eg. Arduino UNO R3)
//                  Breadboard
//                  Jumper Wires
//                  Button 
//                  Transistor 
//                  DC Motor

const int button = 13;
const int motor_controller = 9; 
// middle leg of transistor (switch) set to control motor

// no other const set for DC motor 
// -> it only needs access to Ground and Supply


void setup() {
  // put your setup code here, to run once:
  pinMode(motor_controller, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:

  if ( digitalRead(button) == HIGH) {

    // gradually set motor to max speed
    for (int i = 0; i < 255; i = i + 5) {
        digitalWrite(motor_controller, HIGH);
        delay(50);
    }

    // gradually set motor to zero speed
    for (int i = 0; i < 255; i = i + 5) {
        digitalWrite(motor_controller, HIGH);
        delay(50);
    }
  }

  
}
