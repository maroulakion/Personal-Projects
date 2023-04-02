
// Flows through some colors of RGB

// find me on https://github.com/maroulakion

//RGB LED pins, representing RGB Colors 
const int RED = 8;
const int GREEN = 10;
const int BLUE = 12;

void setRGB(int R, int G, int B);
void changeRGBorder(int R, int G, int B);

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(RED, OUTPUT);
  pinMode(GREEN, OUTPUT);
  pinMode(BLUE, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  int R, G, B;
  changeRGBorder(R, G, B);
  changeRGBorder(G, B, R);
  changeRGBorder(B, R, G);
}

void setRGB(int R, int G, int B) {
  analogWrite(RED, R);
  analogWrite(GREEN, G);
  analogWrite(BLUE, B);
}

void changeRGBorder(int R, int G, int B) {
  for (R = 14; R < 255; R = R + 30) {
    for (G = 14; G < 255; G = G + 40) {
      for (B = 24; B < 255; B = B + 45) {
        //Serial.print("R, G, B = ");
        //Serial.print(R); Serial.print(", "); Serial.print(G); Serial.print(", "); Serial.println(B);
        setRGB(R, G, B);
        delay(3);
      }
    }
  }
}
