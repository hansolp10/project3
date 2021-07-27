#include <Adafruit_Sensor.h>

#include <DHT.h>;

//Constants
#define DHTPIN A1     // what pin we're connected to
#define DHTTYPE DHT11   // DHT 22  (AM2302)
DHT dht(DHTPIN, DHTTYPE); //// Initialize DHT sensor for normal 16mhz Arduino
unsigned long pulse = 0;
int dust = 8;


//Variables
float hum;  //Stores humidity value
float temp; //Stores temperature value
float ugm3;

void setup(){
  Serial.begin(9600);
  dht.begin();
  pinMode(dust, INPUT);
}

void loop(){
    //Read data and store it to variables hum and temp
    hum = dht.readHumidity();
    temp= dht.readTemperature();
    
    pulse = pulseIn(dust, LOW, 20000);
    ugm3 = pulse2ugm3(pulse);
    
    
    Serial.print("Hum: ");
    Serial.print(hum);
    Serial.println(" %");
    Serial.print("Temp: ");
    Serial.print(temp);
    Serial.println(" C");
    
    Serial.print("Dust: ");
    Serial.print(ugm3);
    Serial.println(" ug/m3");

    
    delay(2000); //Delay 2 sec.
}

float pulse2ugm3(unsigned long pulse) {
  float value = (pulse-1400)/14.0;
  if(value > 300) {
    value = 0;
  }
  return value;
}
