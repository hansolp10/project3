#define A1Pin 1

int sensorVal = 0;
int pressure = A0;
int value = 0;

void setup() {
  Serial.begin(9600);
}


void loop() {

  pressure = analogRead(pressure);
  sensorVal = analogRead(A1Pin);  // 토양센서 센서값 읽어 저장

  Serial.print("Asensor: ");
  Serial.println(sensorVal);  // 0(습함) ~ 1023(건조)값 출력
 
  Serial.print("Pressure: ");
  Serial.println(pressure);
 

  delay(2000);

}
