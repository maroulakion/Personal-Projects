
// program and comments written by https://github.com/maroulakion

// TRAFFIC LIGHT WITH BUTTON TO SPEED UP GREEN LIGHT AND BUZZER WHEN TURNED ON
// Hardware needed: Arduino microcontroller (eg. Arduino UNO R3)
//                  Breadboard
//                  Resistors (of 330 Ω)
//                  Jumper wires
//                  3 LEDs (color Red, Yellow, Green)
//                  Button
//                  Buzzer


//LEDs
const int red = 2;
const int yellow = 4;
const int green = 6;
//Sensors and Actuators
const int button = 10;
const int buzzer = 12;
//Program consts
const int inc = 2;
const int speedup = 1500;
const int duration = 4000;
static int counter = 0;

void turn_on_light(int on, int off1, int off2);
void ring_buzzer();

void setup() {
  // put your setup code here, to run once:
  pinMode(red, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(green, OUTPUT); 
  pinMode(button, INPUT);
  pinMode(buzzer, OUTPUT);
}

void loop() {
  // WHILE keeping aware of someone pushing the speed-up button...
  turn_on_light(green, yellow, red);    // turn green ON, and turn OFF the others
  turn_on_light(yellow, green, red);    // turn yellow ON, and turn OFF the others
  turn_on_light(red, yellow, green);    // turn red ON, and turn OFF the others
}

void turn_on_light(int on, int off1, int off2)
{ 
  counter = 0;
  // 1st parameter is the LED that is ON and the others are OFF each time
  digitalWrite(on, HIGH);
  digitalWrite(off1, LOW);
  digitalWrite(off2, LOW);

  // ring buzzer on green light
  // NOTE: if speed-up button pressed on green, ignore
  //       -> counter variable increased inside ring_buzzer()
  if ( on == green )   ring_buzzer();

  // count how long this color needs to be on 
  while (counter<duration) {
    if (digitalRead(button)==0){  
      // NO button pushed
      counter = counter + inc;
      delay(inc);
    }
    else if ( on != green ) 
      //button pushed on a NON - green light
      //speed up timer for the current light
      //in order to make green shine faster
      if ( counter + speedup > duration )
          counter = duration + inc;
      else {
          counter = counter + speedup;
          delay( duration - counter );
          counter = duration + inc;
      }
      
   else {    
      // button pushed while green light is on
      // ignore it, do as the button was never pushed
      // (same instructions)
      counter = counter + inc;
      delay(inc);
    }
   
  }
  
}

void ring_buzzer() {
  int buzzer_duration = 350;
  tone (buzzer, 1000);
  delay ( buzzer_duration );
  noTone (buzzer);
  counter = counter + buzzer_duration;
}
